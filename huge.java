import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class huge {

    private static HashMap<Integer, BigInteger> memo = new HashMap<>();

    public static BigInteger fact(int n) {
        if (n <= 1)
            return BigInteger.ONE;
        if (memo.containsKey(n))
            return memo.get(n);

        BigInteger result = BigInteger.valueOf(n).multiply(fact(n - 1));// N * fact(n-1)
        memo.put(n, result);
        return result;
    }

    public static BigInteger fib(int n) {
        if (n <= 2)
            return BigInteger.ONE;
        if (memo.containsKey(n))
            return memo.get(n);

        BigInteger result = fib(n - 1).add(fib(n - 2));
        memo.put(n, result);
        return result;
    }

    public static BigInteger partitions(int n) {
        if (n == 0)
            return BigInteger.ONE;

        if (n < 0)
            return BigInteger.ZERO;

        if (memo.containsKey(n))
            return memo.get(n);

        BigInteger result = BigInteger.ZERO;
        int k = 1;

        while (true) {
            int pent1 = k * (3 * k - 1) / 2;
            int pent2 = k * (3 * k + 1) / 2;

            if (pent1 > n && pent2 > n)
                break;

            BigInteger sign = (k % 2 == 0) ? BigInteger.valueOf(-1) : BigInteger.ONE;

            if (pent1 <= n)
                result = result.add(sign.multiply(partitions(n - pent1)));

            if (pent2 <= n)
                result = result.add(sign.multiply(partitions(n - pent2)));

            k++;
        }

        memo.put(n, result);
        return result;
    }

    // AGGRESSIVE COWS PROBLEM
    public static int getMaxDistance(int arr[], int N, int C) {
        Arrays.sort(arr);

        int st = 1, end = arr[N - 1] - arr[0], ans = -1;

        while (st <= end) {
            int mid = st + (end - st) / 2;
            if (canPlaceCows(arr, N, C, mid)) {
                ans = mid;
                st = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }

    private static boolean canPlaceCows(int[] arr, int N, int C, int minAllowedDistance) {
        int cows = 1, lastStallPos = arr[0];

        for (int i = 1; i < N; i++) {
            if (arr[i] - lastStallPos >= minAllowedDistance) {
                cows++;
                lastStallPos = arr[i];
            }
            if (cows == C)
                return true;
        }
        return false;
    }

    // PAINTERS PARTITION PROBLEM
    public static int minTimeToPaint(int arr[], int n, int m) {
        int sum = 0, maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            maxVal = Math.max(maxVal, arr[i]);
        }
        int st = maxVal, end = sum, ans = -1;
        while (st <= end) {
            int mid = st + (end - st) / 2;
            if (canPaint(arr, n, m, mid)) {
                ans = mid;
                end = mid - 1;
            } else {
                st = mid + 1;
            }
        }
        return ans;
    }

    public static boolean canPaint(int arr[], int n, int m, int maxAllowedtime) {
        int painters = 1, time = 0;
        for (int i = 0; i < n; i++) {
            if (time + arr[i] <= maxAllowedtime) {
                time += arr[i];
            } else {
                painters++;
                time = arr[i];
            }
        }
        return painters <= m;
    }

    public static void allConstruct(String target, String[] wordBank, List<String> currentPath) {
        if (target.isEmpty()) {
            System.out.println(currentPath);
            return;
        }

        for (String word : wordBank) {
            if (target.startsWith(word)) {
                currentPath.add(word); // Add the word to the current path
                allConstruct(target.substring(word.length()), wordBank, currentPath);
                currentPath.remove(currentPath.size() - 1); // Backtrack
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int arr[] = { 40, 30, 10, 20 };
        int cowStalls[] = { 1, 2, 8, 4, 9 };
        System.out.println(minTimeToPaint(arr, 4, 2));
        System.out.println(getMaxDistance(cowStalls, 5, 3));

        String target = "eeeeeeeee"; // 25 'e's
        String[] wordBank = { "e", "ee", "eee", "eeee", "eeeee" };

        allConstruct(target, wordBank, new ArrayList<>());
    }
}
