package java_practice.graphs;

import java.util.ArrayList;

import java_practice.graphs.Graph.Edge;

public class Bridge {
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

}
