package java_practice.graphs;

import java.util.ArrayList;

import java_practice.graphs.Graph.Edge;

public class DFS {
    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited) {
        System.out.print(curr + " ");
        visited[curr] = true;

        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited, int[] dt, int[] low, int time,
            int parent) {
        visited[curr] = true;
        dt[curr] = low[curr] = ++time;

        for (Edge e : graph[curr]) {
            if (e.dest == parent)
                continue;
            else if (!visited[e.dest]) {
                dfs(graph, e.dest, visited, dt, low, time, curr);
                low[curr] = Math.min(low[curr], low[e.dest]);

                if (dt[curr] < low[e.dest]) {
                    System.out.println("Bridge : " + curr + "---" + e.dest);
                }
            } else {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            }
        }
    }
}
