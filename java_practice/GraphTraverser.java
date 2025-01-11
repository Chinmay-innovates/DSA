package java_practice;

import java.util.ArrayList;

public class GraphTraverser {
    public static void depthFirstSearch(Vertex start, ArrayList<Vertex> visited) {
        System.out.println(start.getData());

        for (Edge e : start.getEdges()) {
            Vertex neighbor = e.getDest();
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                GraphTraverser.depthFirstSearch(neighbor, visited);
            }
        }
    }

    public static void breadthFirstSearch(Vertex start, ArrayList<Vertex> visited) {
        Queue q = new Queue();
        q.enqueue(start);

        while (!q.isEmpty()) {
            Vertex v = q.dequeue();
            System.out.println(v.getData());

            for (Edge e : v.getEdges()) {
                Vertex neighbor = e.getDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    q.enqueue(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Test_graph test = new Test_graph();
        Vertex stVertex = test.getStartVertex();
        ArrayList<Vertex> v1 = new ArrayList<Vertex>();
        ArrayList<Vertex> v2 = new ArrayList<Vertex>();

        v1.add(stVertex);
        v2.add(stVertex);

        System.out.println("BFS: ");
        GraphTraverser.breadthFirstSearch(stVertex, v1);

        System.out.println("DFS: ");
        GraphTraverser.depthFirstSearch(stVertex, v2);
    }
}
