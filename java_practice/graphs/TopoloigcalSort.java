package java_practice.graphs;

import java.util.ArrayList;
import java.util.Stack;
import java_practice.graphs.Graph.Edge;


public class TopoloigcalSort {

    public static void topSortUtil(ArrayList<Edge>[] graph, int curr, boolean[] visited, Stack<Integer> stack) {
        visited[curr] = true;
        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                topSortUtil(graph, e.dest, visited, stack);
            }
        }
        stack.push(curr);
    }

    public static void topSort(ArrayList<Edge>[] graph, int V) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topSortUtil(graph, i, visited, stack);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

}
