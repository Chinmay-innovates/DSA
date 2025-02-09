public class BottomUp {
    public static long fib(int n) {
        long curr = 0, prev1 = 1, prev2 = 1;
        for (int i = 3; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }

    public static long fact(int n) {
        long curr = 1;
        for (int i = 2; i <= n; i++) {
            curr *= i;
        }
        return curr;
    }

    public static void gridTraveller(int m, int n) {
        long[][] dp = new long[m + 1][n + 1];
        dp[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > 1)
                    dp[i][j] += dp[i - 1][j]; // move down
                if (j > 1)
                    dp[i][j] += dp[i][j - 1]; // move right
            }
        }
    }

    public static void printResult(long[] results) {
        for (long result : results) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        int[] inputs = { 5, 8, 9, 55, 35 };
        long[] results = new long[inputs.length];

        long startTime = System.nanoTime();
        for (int i = 0; i < inputs.length; i++) {
            results[i] = fact(inputs[i]);
        }
        long endTime = System.nanoTime();

        printResult(results);

        System.out.println("Time taken (computation only): " + (endTime - startTime) + " nanoseconds.");
    }
}
