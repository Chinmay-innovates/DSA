package java_practice.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java_practice.graphs.Graph.Edge;

public class Astar {
    public static class Pair implements Comparable<Pair> {
        int node, cost;

        public Pair(int n, int c) {
            this.node = n;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair p2) {
            return Integer.compare(this.cost, p2.cost);
        }
    }

    public static void aStar(ArrayList<Edge>[] graph, int src, int tar, int V, int[] h) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[src] = 0;
        pq.add(new Pair(src, h[src])); // Use f(n) = g(n) + h(n)

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;

            if (u == tar)
                break;

            if (visited[u])
                continue;
            visited[u] = true;

            for (Edge e : graph[u]) {
                int v = e.dest, weight = e.wt;

                if (!visited[v] && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.add(new Pair(v, dist[v] + h[v])); // Use f(n)
                }
            }
        }

        // Construct and print path
        if (dist[tar] == Integer.MAX_VALUE) {
            System.out.println("No path found.");
            return;
        }

        List<Integer> path = new ArrayList<>();
        for (int i = tar; i != -1; i = parent[i]) {
            path.add(i);
        }
        Collections.reverse(path);

        System.out.println("Shortest path: " + path);

    }
}