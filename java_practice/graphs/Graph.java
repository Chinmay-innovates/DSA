package java_practice.graphs;

import java.util.ArrayList;
import java.util.Random;

public class Graph {

    public static class Edge {

        int src, dest, wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void getBridge(ArrayList<Edge>[] graph, int V) {

        int time = 0;
        boolean[] visited = new boolean[V];
        int[] dt = new int[V], low = new int[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFS.dfs(graph, i, visited, dt, low, time, -1);
            }
        }
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, boolean[] visited, int curr, String path, int tar) {
        if (curr == tar) {
            System.out.println(path);
            return;
        }
        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                visited[curr] = true;
                printAllPaths(graph, visited, e.dest, path + e.dest, tar);
                visited[curr] = false;
            }
        }
    }

    public static void printGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.dest + ", " + e.wt + ") ");
            }
            System.out.println();
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        Random rand = new Random();

        // Ensure connectivity (Spanning Tree)
        for (int i = 1; i < graph.length; i++) {
            int parent = rand.nextInt(i); // Connect to any previous node
            int weight = rand.nextInt(20) + 1;
            graph[parent].add(new Edge(parent, i, weight));
            graph[i].add(new Edge(i, parent, weight)); // Undirected
        }

        // Add extra edges for complexity
        int extraEdges = (int) (graph.length * 1.5);
        for (int i = 0; i < extraEdges; i++) {
            int u = rand.nextInt(graph.length);
            int v = rand.nextInt(graph.length);
            if (u != v) {
                int weight = rand.nextInt(20) + 1;
                graph[u].add(new Edge(u, v, weight));
                graph[v].add(new Edge(v, u, weight));
            }
        }
    }
}
