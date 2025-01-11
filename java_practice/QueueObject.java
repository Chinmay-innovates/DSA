package java_practice;

public class QueueObject implements Comparable<QueueObject> {
    Vertex vertex;
    int distance;

    QueueObject(Vertex vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(QueueObject o) {
        return Integer.compare(this.distance, o.distance);
    }
}