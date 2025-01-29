package java_practice.graphs;

import java.util.ArrayList;

import java_practice.graphs.Graph.Edge;

public class BellmanFord {

    public static void bellmanFord(ArrayList<Edge>[] graph, int src, int V) {
        int[] dist = new int[V];

        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // Relax all edges V-1 times
        for (int i = 0; i < V; i++) { // Includes one extra pass for cycle detection
            boolean updated = false;

            for (int j = 0; j < V; j++) {
                for (Edge e : graph[j]) {
                    if (dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.wt < dist[e.dest]) {
                        dist[e.dest] = dist[e.src] + e.wt;
                        updated = true;
                    }
                }
            }

            // If no update occurs, exit early (optimization)
            if (!updated)
                break;

            /*
             * On the V-th iteration, if an update still happens,
             * there's a negative cycle
             */
            if (i == V - 1) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
    }
}
