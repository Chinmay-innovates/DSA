package java_practice.graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

import java_practice.graphs.Graph.Edge;

public class Prims {
    public static class Pair implements Comparable<Pair> {
        int node, cost, parent;

        public Pair(int n, int c, int p) {
            this.node = n;
            this.cost = c;
            this.parent = p;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost; // Min-Heap (ascending order)
        }
    }

    // ElogE
    public static void primsAlgo(ArrayList<Edge>[] graph, int V) {

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        ArrayList<Edge> mstEdges = new ArrayList<>();
        boolean[] visited = new boolean[V];
        int mstCost = 0;

        pq.add(new Pair(0, 0, -1));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!visited[curr.node]) {
                visited[curr.node] = true;
                mstCost += curr.cost;

                if (curr.parent != -1) {
                    mstEdges.add(new Edge(curr.parent, curr.node, curr.cost));
                }

                for (Edge e : graph[curr.node]) {
                    if (!visited[e.dest]) {
                        pq.add(new Pair(e.dest, e.wt, curr.node));
                    }
                }
            }
        }

        System.out.println("Minimum MST cost: " + mstCost);

        System.out.println("MST Edges:");
        for (Edge e : mstEdges) {
            System.out.println(e.src + " - " + e.dest + " (" + e.wt + ")");
        }
    }
}
