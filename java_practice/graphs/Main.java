package java_practice.graphs;

import java.util.ArrayList;
import java_practice.graphs.Graph.Edge;

public class Main {
    public static void main(String[] args) {
        int V = 9;
        ArrayList<Edge>[] graph = new ArrayList[V];
        Graph.createGraph(graph);

        System.out.println("Print Graph : ");
        Graph.printGraph(graph);

        System.out.println("Print all paths : ");
        Graph.printAllPaths(graph, new boolean[V], 0, " ", 8);
        System.out.println("Print all paths Complete");

        System.out.println("BFS Traversal:");
        BFS.bfs(graph, V);
        System.out.println("\nBFS Complete");

        System.out.println("\nDFS Traversal:");
        DFS.dfs(graph, 0, new boolean[V]);
        System.out.println("\nDFS Complete");

        System.out.println("\nDijkstra Algorithm:");
        Dijkstra.dijkstra(graph, 0, V);

        System.out.println("\nBellman-Ford Algorithm:");
        BellmanFord.bellmanFord(graph, 0, V);

        int[] h = new int[V];
        for (int i = 0; i < V; i++) {
            h[i] = Math.abs(i - (V - 1)); // Example heuristic: Distance from node to target
        }

        Astar.aStar(graph, 0, V - 1, V, h);

        System.out.println("\nhas directed cycle : " +
                CycleDetection.hasDirectedCycle(graph, 0, new boolean[V], new boolean[V]));
        System.out.println("has directed cycle : " +
                CycleDetection.hasUndirectedCycle(graph, 0, new boolean[V], -1));

        System.out.println("\nPrims Algorithm:");
        Prims.primsAlgo(graph, V);

        System.out.println("\nTopological Sort:");
        TopoloigcalSort.topSort(graph, V);

        System.out.println("\nKosaraju Algorithm:");
        Kosaraju.kosarajuAlgo(graph, V);

        System.out.println("\nTarjan Algorithm:");
        Tarjan.getArticulationPoint(graph, V);

        System.out.println("\nBridges :");
        Bridge.getBridge(graph, V);

    }
}
