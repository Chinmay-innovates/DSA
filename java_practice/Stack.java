package java_practice;

import java.util.*;

class StackImpl {
    private List<Integer> stack;
    private int[] incArray;

    public StackImpl() {
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

    public boolean validParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        System.out.println(s);
        for (char c : s.toCharArray()) {
            System.out.println(c);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;

                if ((c == ')' && stack.peek() == '(') ||
                        (c == '}' && stack.peek() == '{') ||
                        (c == ']' && stack.peek() == '['))
                    stack.pop();
                else
                    return false;

            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        StackImpl s = new StackImpl();
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
        System.out.println(
                s.validParenthesis("{()}[]"));
    }
}
