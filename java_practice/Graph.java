package java_practice;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean inputIsWeighted, boolean inputIsDirected) {
        this.vertices = new ArrayList<Vertex>();
        this.isWeighted = inputIsWeighted;
        this.isDirected = inputIsDirected;
    }

    public Graph() {
        Graph graph = new Graph(true, false);
        Vertex src = graph.addVertex("VS");
        Vertex v1 = graph.addVertex("V0");
        Vertex v2 = graph.addVertex("V1");

        Vertex v11 = graph.addVertex("V1.1");
        Vertex v12 = graph.addVertex("V1.2");
        Vertex v21 = graph.addVertex("V2.1");

        Vertex v111 = graph.addVertex("V1.1.1");
        Vertex v112 = graph.addVertex("V1.1.2");
        Vertex v121 = graph.addVertex("V1.2.1");
        Vertex v211 = graph.addVertex("V2.1.1");

        graph.addEdge(src, v1, null);
        graph.addEdge(src, v2, null);

        graph.addEdge(v1, v11, null);
        graph.addEdge(v1, v12, null);
        graph.addEdge(v2, v21, null);

        graph.addEdge(v11, v111, null);
        graph.addEdge(v11, v112, null);
        graph.addEdge(v12, v121, null);
        graph.addEdge(v21, v211, null);

        // cycle
        graph.addEdge(v21, v2, null);

    }

    public Vertex addVertex(String inputData) {
        Vertex newVertex = new Vertex(inputData);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex startVertex, Vertex endVertex, Integer weight) {
        if (!this.isWeighted)
            weight = null;

        startVertex.addEdge(endVertex, weight);

        if (!this.isDirected) {
            endVertex.addEdge(startVertex, weight);
        }
    }

    public void removeEdge(Vertex startVertex, Vertex endVertex) {
        startVertex.removeEdge(endVertex);
        if (!this.isDirected) {
            endVertex.removeEdge(startVertex);
        }
    }

    public void removeVertex(Vertex vertex) {
        this.vertices.remove(vertex);
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public boolean getIsWeighted() {
        return this.isDirected;
    }

    public boolean getIsDirected() {
        return this.isWeighted;
    }

    public Vertex getVertexByValue(String value) {
        for (Vertex v : this.vertices) {
            if (v.getData().equals(value)) {
                return v;
            }
        }
        return null;
    }

    public void print() {
        for (Vertex v : this.vertices) {
            v.print(isWeighted);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(true, false);
        Vertex src = graph.addVertex("VS");
        Vertex v1 = graph.addVertex("V0");
        Vertex v2 = graph.addVertex("V1");

        Vertex v11 = graph.addVertex("V1.1");
        Vertex v12 = graph.addVertex("V1.2");
        Vertex v21 = graph.addVertex("V2.1");

        Vertex v111 = graph.addVertex("V1.1.1");
        Vertex v112 = graph.addVertex("V1.1.2");
        Vertex v121 = graph.addVertex("V1.2.1");
        Vertex v211 = graph.addVertex("V2.1.1");

        graph.addEdge(src, v1, null);
        graph.addEdge(src, v2, null);

        graph.addEdge(v1, v11, null);
        graph.addEdge(v1, v12, null);
        graph.addEdge(v2, v21, null);

        graph.addEdge(v11, v111, null);
        graph.addEdge(v11, v112, null);
        graph.addEdge(v12, v121, null);
        graph.addEdge(v21, v211, null);

        // cycle
        graph.addEdge(v21, v2, null);

        graph.print();
    }
}
