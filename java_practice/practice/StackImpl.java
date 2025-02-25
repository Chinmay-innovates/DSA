package java_practice.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
        for (char c : s.toCharArray()) {
            System.out.println(c);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;

                if ((c == ')' && stack.peek() == '(') || (c == '}' && stack.peek() == '{')
                        || (c == ']' && stack.peek() == '['))
                    stack.pop();
                else
                    return false;

            }
        }
        return stack.isEmpty();
    }

    public static void nextGreaterElement(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] result = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= arr[i]) {
                s.pop();
            }
            if (s.isEmpty())
                result[i] = -1;
            else
                result[i] = s.peek();
            s.push(arr[i]);
        }

        for (int val : result) {
            System.out.print(val + " ");
        }
    }

    public static void prevSmallerElement(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!s.isEmpty() && s.peek() >= arr[i]) {
                s.pop();
            }
            if (s.isEmpty())
                result[i] = -1;
            else
                result[i] = s.peek();
            s.push(arr[i]);
        }

        for (int val : result) {
            System.out.print(val + " ");
        }
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int[] left = new int[n], right = new int[n];

        Arrays.fill(left, 0); // previous smaller element
        Arrays.fill(right, 0); // next smaller element

        // Right smaller
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 0 && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            stack.pop();
        }

        // Left smaller
        for (int i = 0; i < n; i++) {
            while (stack.size() > 0 && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int currArea = heights[i] * (right[i] - left[i] - 1); // height * width
            maxArea = Math.max(maxArea, currArea);
        }

        return maxArea;
    }

    public static int[] nextGreaterElement_1(int[] nums1, int[] nums2) {
        // nums2 is a subset of nums1
        // len(nums1) >= len(nums2)
        Stack<Integer> s = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums1.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() <= nums1[i]) {
                s.pop();
            }
            if (s.isEmpty())
                map.put(nums1[i], -1);
            else
                map.put(nums1[i], s.peek());
            s.push(nums1[i]);
        }

        int[] result = new int[nums2.length];
        for (int i = 0; i < nums2.length; i++) {
            result[i] = map.get(nums2[i]);
            System.out.print(result[i] + " ");
        }
        return result;
    }

    public static int trap(int[] height) {

        int ans = 0, n = height.length;
        int left = 0, right = n - 1, leftMax = 0, rightMax = 0;

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }

    public static int getCelebrity(int[][] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            stack.push(i);
        }

        while (stack.size() > 1) {
            int i = stack.peek();
            stack.pop();

            int j = stack.peek();
            stack.pop();

            if (arr[i][j] == 0) {
                stack.push(i);
            } else {
                stack.push(j);
            }
        }

        int celeb = stack.peek();

        for (int i = 0; i < arr.length; i++) {
            if (i != celeb && (arr[celeb][i] == 1 || arr[i][celeb] == 0)) {
                return -1;
            }
        }
        return celeb;
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
        System.out.println(s.validParenthesis("{()}[]"));

        int[] nums1 = {6, 8, 0, 1, 2, 4};
        int[] nums2 = {6, 2, 4};

        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));
        nextGreaterElement_1(nums1, nums2);
        prevSmallerElement(nums1);
        prevSmallerElement(nums2);

        System.out.println(trap(new int[] {4, 2, 0, 3, 2, 5}));
        System.out.println(trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

        System.out
                .println("Celeb is " + getCelebrity(new int[][] {{1, 0, 0}, {0, 0, 0}, {0, 1, 0}})); // -1
        System.out
                .println("Celeb is " + getCelebrity(new int[][] {{0, 1, 0}, {0, 0, 0}, {0, 1, 0}})); // 1
        System.out
                .println("Celeb is " + getCelebrity(new int[][] {{0, 1, 1}, {0, 0, 1}, {0, 0, 0}})); // 2
    }
}


class MinStack {
    Stack<Long> stack = new Stack<>();

    long minVal;

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push((long) val);
            minVal = val;
        }

        else {
            if (val < minVal) {
                stack.push(2L * val - minVal);
                minVal = val;
            } else
                stack.push((long) val);
        }

    }

    public void pop() {
        if (stack.isEmpty()) // edge case
            return;

        if (stack.peek() < minVal)
            minVal = 2 * minVal - stack.peek();
        else
            stack.pop();

        if (stack.isEmpty())
            minVal = Integer.MAX_VALUE; // Reset minVal when stack becomes empty
    }

    public Integer top() {
        if (stack.isEmpty()) // edge case
            return null;

        if (stack.peek() < minVal)
            return (int) minVal;

        return stack.peek().intValue();
    }

    public Integer getMin() {
        return stack.isEmpty() ? null : (int) minVal;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // Output: -3
        minStack.pop();
        System.out.println(minStack.top()); // Output: 0
        System.out.println(minStack.getMin()); // Output: -2
    }
}
