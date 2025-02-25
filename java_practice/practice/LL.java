package java_practice.practice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LL {
    Node head;
    private int size;

    LL() {
        this.size = 0;
    }

    class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
            size++;
        }
    }

    public void addFirst(String data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(String data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }
        Node currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }
        currNode.next = newNode;
    }

    public void print() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node currNode = head;

        while (currNode != null) {
            System.out.print(currNode.data + " -> ");
            currNode = currNode.next;
        }
        System.out.println("NULL");
    }

    public void removeFirst() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        size--;
        head = head.next;
        System.out.println("First element deleted");
    }

    public void removeLast() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        size--;

        if (head.next == null) {
            head = null;
            System.out.println("List is now empty");
            return;
        }

        Node currNode = head;

        while (currNode.next.next != null) {
            currNode = currNode.next;
        }
        currNode.next = null;
        System.out.println("Last element deleted");
    }

    public void remove(String data) {
        if (head == null) {
            System.out.println("Cannot delete, list is empty");
            return;
        }

        Node currNode = head;

        while (currNode.next != null) {
            if (currNode.next.data.equals(data)) {
                currNode.next = currNode.next.next;
                size--;
                System.out.println("Data '" + data + "' deleted");
                return;
            }
            currNode = currNode.next;
        }
        System.out.println("Data '" + data + "' not found");
    }

    public int getSize() {
        return size;
    }

    public Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public Node reverseIterative(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node prevNode = head;
        Node currNode = head.next;

        while (currNode != null) {
            Node nextNode = currNode.next;
            currNode.next = prevNode;

            prevNode = currNode;
            currNode = nextNode;
        }
        head.next = null;

        return prevNode;
    }

    public static void main(String[] args) {
        LL list = new LL();

        list.addFirst("c");
        list.print();
        list.addFirst("b");
        list.print();
        list.addFirst("a");
        list.print();

        list.addLast("d");
        list.print();
        list.addLast("e");
        list.print();
        list.addLast("f");
        list.print();
        list.addLast("g");
        list.print();
        list.addLast("h");
        list.print();

        // list.remove("b");
        // list.removeFirst();
        // list.removeLast();

        list.print();
        list.head = list.reverseIterative(list.head);
        list.print();

        // System.out.println("Size: " + list.getSize());

        // LinkedList<Integer> ll = new LinkedList<Integer>();
        // ll.addFirst(5);
        // ll.addLast(6);
        // ll.addLast(7);
        // ll.addLast(8);

        // System.out.println(ll);
        // System.out.println(ll.size());

        // for (int s : ll) {
        // System.out.print(s + " -> ");
        // }
        // System.out.println("NULL");

    }
}


class LRUCache {
    class Node {
        int key, val;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            prev = next = null;
        }
    }

    private Map<Integer, Node> map = new HashMap<>();
    private final int capacity;
    private final Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public void insertNode(Node newNode) {
        Node oldNext = head.next;

        head.next = newNode;
        oldNext.prev = newNode;

        newNode.next = oldNext;
        newNode.prev = head;
    }

    public void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }


    /**
     * Returns the value associated with the given key if present in the cache, otherwise returns
     * -1. Also, moves the entry to the front if present.
     *
     * @param key The key of the entry to be retrieved from the cache.
     * @return The value associated with the key if present, otherwise -1.
     */
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node ansNode = map.get(key);
        int ans = ansNode.val;

        map.remove(key);
        deleteNode(ansNode);

        insertNode(ansNode);
        map.put(key, ansNode);
        return ans;
    }

    /**
     * Inserts a key-value pair into the LRU cache. If the key already exists, it updates the value
     * and moves the entry to the front. If the cache is at capacity, it removes the least recently
     * used item before adding the new one.
     *
     * @param key The key of the entry to be added or updated in the cache.
     * @param value The value associated with the key.
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            deleteNode(node);
            map.remove(key);
        }

        if (map.size() == capacity) {
            // Remove least recently data
            map.remove(tail.prev.key);
            deleteNode(tail.prev);
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        insertNode(newNode);
    }



    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));

        cache.put(3, 3);
        System.out.println(cache.get(2));

        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

        System.out.println("LRUCache.main()");

        LRUCache cache2 = new LRUCache(3);
        cache2.put(1, 10);
        cache2.put(2, 20);
        cache2.put(3, 30);
        System.out.println(cache2.get(2));

        cache2.put(4, 40);
        System.out.println(cache2.get(1));

        cache2.put(2, 25);
        cache2.put(5, 50);

        System.out.println(cache2.get(3));
        System.out.println(cache2.get(2));
        System.out.println(cache2.get(4));
        System.out.println(cache2.get(5));

    }
}


class OptimizedLRUCache extends LinkedHashMap<Integer, Integer> {
    private final int capacity;

    public OptimizedLRUCache(int capacity) {
        // true for access-order, false for insertion-order.
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
