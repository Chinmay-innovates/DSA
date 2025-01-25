package java_practice;

import java.util.*;

class Stack {
    private List<Integer> stack;
    private int[] incArray;

    public Stack() {
        this.stack = new ArrayList<>();
        this.incArray = new int[1000];
    }

    public void push(int x) {
        stack.add(x);
    }

    public Integer pop() {
        if (!stack.isEmpty()) {
            int index = stack.size() - 1;
            int value = stack.remove(index) + incArray[index];
            if (index > 0) {
                incArray[index - 1] += incArray[index];
            }
            incArray[index] = 0;
            return value;
        }
        return null;
    }

    public Integer peek() {
        if (!stack.isEmpty()) {
            int index = stack.size() - 1;
            return stack.get(index) + incArray[index];
        }
        return null;
    }

    public void inc(int i, int v) {
        int limit = Math.min(i, stack.size()) - 1;
        if (limit >= 0) {
            incArray[limit] += v;
        }
    }

    public void printStack() {
        List<Integer> tempStack = new ArrayList<>();
        for (int i = 0; i < stack.size(); i++) {
            tempStack.add(stack.get(i) + incArray[i]);
        }
        System.out.println(tempStack);
    }

    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(3);
        s.push(5);
        s.push(7);
        s.printStack(); // Expected output: [3, 5, 7]
        s.inc(2, 10); // Increase bottom 2 elements by 10
        s.printStack(); // Expected output: [13, 15, 7]
        s.pop();
        s.printStack(); // Expected output: [13, 15]
        s.push(8);
        s.push(2);
        s.inc(3, 5);
        s.printStack(); // Expected output: [18, 20, 12, 2]
    }
}
