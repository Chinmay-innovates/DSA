package java_practice.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void bfs(ArrayList<Edge>[] graph, int V) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (!visited[curr]) {
                visited[curr] = true;
                System.out.print(curr + " ");

                for (Edge e : graph[curr]) {
                    q.add(e.dest);
                }
            }
        }
    }
}