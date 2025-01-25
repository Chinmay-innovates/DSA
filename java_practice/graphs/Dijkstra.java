package java_practice.graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

// O(E + ElogV)
// ElogV priority queue (sorting) -> nlogN;
// E -> traversing all edges once
public class Dijkstra {
    public static class Pair implements Comparable<Pair> {
        int node, dist;

        public Pair(int n, int d) {
            this.node = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist; // ascending
        }
    }

    public static void dijkstra(ArrayList<Edge>[] graph, int src, int V) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        boolean[] visited = new boolean[V];
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!visited[curr.node]) {
                visited[curr.node] = true;
                for (Edge e : graph[curr.node]) {
                    int u = e.src, v = e.dest;
                    if (dist[u] + e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
    }
}
