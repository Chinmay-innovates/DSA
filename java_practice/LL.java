package java_practice;


class LL {
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