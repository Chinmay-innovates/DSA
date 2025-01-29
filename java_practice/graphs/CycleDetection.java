package java_practice.graphs;

import java.util.ArrayList;


public class CycleDetection {
    public static boolean hasDirectedCycle(ArrayList<Edge>[] graph, int curr, boolean[] visited, boolean[] recStack) {
        visited[curr] = true;
        recStack[curr] = true;
        for (Edge e : graph[curr]) {
            if (recStack[e.dest]) {
                return true;
            } else if (!visited[e.dest] &&
                    hasDirectedCycle(graph, e.dest, visited, recStack)) {
                return true;
            }
        }
        recStack[curr] = false;
        return false;
    }

    public static boolean hasUndirectedCycle(ArrayList<Edge>[] graph, int curr, boolean[] visited, int parent) {
        visited[curr] = true;

        for (Edge e : graph[curr]) {
            if (visited[e.dest] && e.dest != parent)
                return true;
            else if (!visited[e.dest] && hasUndirectedCycle(graph, e.dest, visited, parent))
                return true;
        }
        return false;
    }
}
