package java_practice.graphs;

import java.util.ArrayList;

import java_practice.graphs.Graph.Edge;



public class Tarjan {
    private static void tarjansAlgo(ArrayList<Edge>[] graph, int curr, boolean[] visited, boolean[] ap,
            int[] dt,
            int[] low,
            int time,
            int parent) {
        visited[curr] = true;
        dt[curr] = low[curr] = ++time;
        int children = 0;

        for (Edge e : graph[curr]) {
            int neighbor = e.dest;
            if (parent == neighbor)
                continue;
            else if (visited[neighbor]) { // AP condn
                low[curr] = Math.min(low[curr], dt[neighbor]);
            } else {
                tarjansAlgo(graph, neighbor, visited, ap, dt, low, time, curr);
                low[curr] = Math.min(low[curr], low[neighbor]);

                if (dt[curr] <= low[neighbor] && parent != -1) {
                    ap[curr] = true;
                }
                children++;
            }
        }

        if (parent == -1 && children > 1) {
            ap[curr] = true;
        }
    }

    public static void getArticulationPoint(ArrayList<Edge>[] graph, int V) {
        int[] dt = new int[V], low = new int[V];
        boolean[] visited = new boolean[V], ap = new boolean[V];
        int time = 0;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                tarjansAlgo(graph, i, visited, ap, dt, low, time, -1);
            }
        }

        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.println("AP : " + i);
            }
        }

    }
}