package java_practice;

import java.util.*;

public class Dijkstras {
    public static Map<String, Integer> dijkstra(Graph g, Vertex stVertex) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, Vertex> prev = new HashMap<>();
        PriorityQueue<QueueObject> queue = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();

        for (Vertex v : g.getVertices()) {
            dist.put(v.getData(), Integer.MAX_VALUE);
            prev.put(v.getData(), null);
        }

        // Set start vertex distance to 0
        dist.put(stVertex.getData(), 0);
        queue.add(new QueueObject(stVertex, 0));

        while (!queue.isEmpty()) {
            QueueObject curr = queue.poll();
            Vertex currVertex = curr.vertex;

            if (visited.contains(currVertex.getData()))
                continue;
            visited.add(currVertex.getData());

            for (Edge e : currVertex.getEdges()) {
                Vertex neighbor = e.getDest();
                int newDist = dist.get(currVertex.getData()) + e.getWeight();

                if (newDist < dist.get(neighbor.getData())) {
                    dist.put(neighbor.getData(), newDist);
                    prev.put(neighbor.getData(), currVertex);
                    queue.add(new QueueObject(neighbor, newDist));
                }
            }
        }

        return dist;
    }

    public static void dijkstraResultPrinter(Map<String, Integer> dist) {
        System.out.println("Distances:");
        for (Map.Entry<String, Integer> entry : dist.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true, true);
        Vertex a = graph.addVertex("A");
        Vertex b = graph.addVertex("B");
        Vertex c = graph.addVertex("C");
        Vertex d = graph.addVertex("D");
        Vertex e = graph.addVertex("E");
        Vertex f = graph.addVertex("F");
        Vertex g = graph.addVertex("G");

        graph.addEdge(a, c, 100);
        graph.addEdge(a, b, 3);
        graph.addEdge(a, d, 4);
        graph.addEdge(d, c, 3);
        graph.addEdge(d, e, 8);
        graph.addEdge(e, b, 2);
        graph.addEdge(e, f, 10);
        graph.addEdge(b, g, 9);
        graph.addEdge(e, g, 50);

        dijkstraResultPrinter(dijkstra(graph, a));
    }
}