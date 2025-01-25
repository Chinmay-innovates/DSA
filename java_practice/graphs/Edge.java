package java_practice.graphs;

public class Edge {
    public int src, dest, wt;

    public Edge(int s, int d) {
        this.src = s;
        this.dest = d;
    }

    public Edge(int s, int d, int w) {
        this.src = s;
        this.dest = d;
        this.wt = w;
    }
}
