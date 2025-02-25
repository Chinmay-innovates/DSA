package java_practice.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import java_practice.graphs.Graph.Edge;

class Pair implements Comparable<Pair> {

    int node, cost;

    Pair(int n, int c) {
        this.node = n;
        this.cost = c;
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(this.cost, o.cost);
    }

}

public class GraphGenerator {

    public static List<List<Edge>> createGraph(int V, int E) {
        if (E > V * (V - 1)) {
            throw new IllegalArgumentException("Too many edges for the given number of vertices.");
        }

        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        Random random = new Random();
        int edgesAdded = 0;

        while (edgesAdded < E) {
            int u = random.nextInt(V); // Random source node
            int v = random.nextInt(V); // Random destination node
            int wt = random.nextInt(100) + 1; // Random weight between 1 and 100

            // Avoid self-loops and duplicate edges
            if (u != v && !isEdgeExists(graph, u, v)) {
                graph.get(u).add(new Edge(v, wt));
                edgesAdded++;
            }
        }

        return graph;
    }

    private static boolean isEdgeExists(List<List<Edge>> graph, int u, int v) {
        for (Edge e : graph.get(u)) {
            if (e.dest == v) {
                return true;
            }
        }
        return false;
    }

    public static void printGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("Node " + i + " -> ");
            for (Edge e : graph.get(i)) {
                System.out.print("(" + e.dest + ", " + e.wt + ") ");
            }
            System.out.println();
        }
    }

    // A* algorithm
    public static List<Integer> findShortestPath(List<List<Edge>> graph, int src, int tar, int V) {
        int[] h = calculateHeuristic(graph);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[src] = 0;
        pq.add(new Pair(src, h[src])); // f(n) = g(n) + h(n)

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;

            if (u == tar)
                break;

            if (visited[u])
                continue;
            visited[u] = true;

            for (Edge e : graph.get(u)) {
                int v = e.dest, wt = e.wt;

                if (!visited[v] && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                    parent[v] = u;
                    pq.add(new Pair(v, dist[v] + h[v])); // f(n) = g(n) + h(n)
                }
            }
        }

        // Reconstruct the path
        List<Integer> path = new ArrayList<>();
        if (dist[tar] != Integer.MAX_VALUE) {
            for (int i = tar; i != -1; i = parent[i]) {
                path.add(i);
            }
            Collections.reverse(path);
        }

        return path;
    }

    // Calculate heuristic values (degree-based heuristic)
    private static int[] calculateHeuristic(List<List<Edge>> graph) {
        int V = graph.size();
        int[] h = new int[V];
        for (int i = 0; i < V; i++) {
            h[i] = graph.get(i).size(); // Degree of the node
        }
        return h;
    }

    public static void main(String[] args) {
        int V = 1000; // Number of vertices
        int E = 5000; // Number of edges

        // Generate a graph
        List<List<Edge>> graph = createGraph(V, E);

        printGraph(graph);
        GraphGUI.printGraphGUI(graph);

        int src = 0, tar = 9;
        System.out.println("Shortest path: " + findShortestPath(graph, src, tar, V));
    }
}
