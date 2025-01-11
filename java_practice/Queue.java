package java_practice;

import java.util.LinkedList;

public class Queue {
    @SuppressWarnings("rawtypes")
    public LinkedList queue;
    public int size;

    @SuppressWarnings("rawtypes")
    public Queue() {
        this.queue = new LinkedList();
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @SuppressWarnings("unchecked")
    public void enqueue(Vertex value) {
        this.queue.addLast(value);
        this.size++;
    }

    public Vertex peek() {
        if (!this.isEmpty()) {
            return (Vertex) this.queue.getFirst();
        } else {
            throw new Error("Queue is empty");
        }
    }

    public Vertex dequeue() {
        if (!this.isEmpty()) {

            Vertex value = (Vertex) this.queue.removeFirst();
            this.size--;
            return value;
        } else {
            throw new Error("Queue is empty");
        }
    }

}
