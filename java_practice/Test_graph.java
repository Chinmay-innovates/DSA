package java_practice;

public class Test_graph {
    private Graph graph;

    public Test_graph() {
        this.graph = new Graph(true, false);
        Vertex src = this.graph.addVertex("VS");
        Vertex v1 = this.graph.addVertex("V0");
        Vertex v2 = this.graph.addVertex("V1");

        Vertex v11 = this.graph.addVertex("V1.1");
        Vertex v12 = this.graph.addVertex("V1.2");
        Vertex v21 = this.graph.addVertex("V2.1");

        Vertex v111 = this.graph.addVertex("V1.1.1");
        Vertex v112 = this.graph.addVertex("V1.1.2");
        Vertex v121 = this.graph.addVertex("V1.2.1");
        Vertex v211 = this.graph.addVertex("V2.1.1");

        this.graph.addEdge(src, v1, null);
        this.graph.addEdge(src, v2, null);

        this.graph.addEdge(v1, v11, null);
        this.graph.addEdge(v1, v12, null);
        this.graph.addEdge(v2, v21, null);

        this.graph.addEdge(v11, v111, null);
        this.graph.addEdge(v11, v112, null);
        this.graph.addEdge(v12, v121, null);
        this.graph.addEdge(v21, v211, null);

        // cycle
        this.graph.addEdge(v21, v2, null);
    }

    public Vertex getStartVertex() {
        return this.graph.getVertices().get(0);
    }
}
