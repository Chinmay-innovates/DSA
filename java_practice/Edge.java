package java_practice;

public class Edge {
    private Vertex src;
    private Vertex dest;
    private Integer weight;

    public Edge(Vertex srcV, Vertex destV, Integer inputWeight) {
        this.src = srcV;
        this.dest = destV;
        this.weight = inputWeight;
    }

    public Vertex getSrc() {
        return this.src;
    }

    public Vertex getDest() {
        return this.dest;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public static void main(String[] args) {

    }
}
