package java_practice.graphs;

import java.util.ArrayList;

public class Graph {
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

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        /*
         * (0) —— (1) —— (2)
         * | | |
         * (3) —— (4) —— (5) —— (6)
         * / \
         * (7) (8)
         */
        // Undirected Edges
        graph[0].add(new Edge(0, 1, 4));
        graph[0].add(new Edge(0, 3, 3));

        graph[1].add(new Edge(1, 0, 4));
        graph[1].add(new Edge(1, 2, 2));
        graph[1].add(new Edge(1, 4, 1));

        graph[2].add(new Edge(2, 1, 2));
        graph[2].add(new Edge(2, 5, 5));

        graph[3].add(new Edge(3, 0, 3));
        graph[3].add(new Edge(3, 4, 7));

        graph[4].add(new Edge(4, 1, 1));
        graph[4].add(new Edge(4, 3, 7));
        graph[4].add(new Edge(4, 5, 6));
        graph[4].add(new Edge(4, 7, 3));
        graph[4].add(new Edge(4, 8, 2));

        graph[5].add(new Edge(5, 2, 5));
        graph[5].add(new Edge(5, 4, 6));
        graph[5].add(new Edge(5, 6, 8));

        graph[6].add(new Edge(6, 5, 8));

        graph[7].add(new Edge(7, 4, 3));
        graph[8].add(new Edge(8, 4, 2));
    }
}
