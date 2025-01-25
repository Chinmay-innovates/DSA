package java_practice.graphs;

import java.util.ArrayList;
import java.util.Stack;

public class Kosaraju {

    public static void kosarajuAlgo(ArrayList<Edge>[] graph, int V) {
        // STEP 1
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                TopoloigcalSort.topSortUtil(graph, i, visited, stack);
            }
        }

        // STEP 2
        @SuppressWarnings("unchecked")

        ArrayList<Edge>[] transpose = new ArrayList[V];
        for (int i = 0; i < graph.length; i++) {
            visited[i] = false;
            transpose[i] = new ArrayList<>();
        }
        for (ArrayList<Edge> edges : graph) {
            for (Edge e : edges) {
                transpose[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        // STEP 3
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited[curr]) {
                DFS.dfs(transpose, curr, visited);
                System.out.println();
            }
        }
    }

}
