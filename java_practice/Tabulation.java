package java_practice;

public class Tabulation {

    public static long fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static long gridTraveller(int m, int n) {
        long[][] dp = new long[m + 1][n + 1];
        dp[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                long current = dp[i][j];
                if (i + 1 <= m)
                    dp[i + 1][j] += current; // Move Down
                if (j + 1 <= n)
                    dp[i][j + 1] += current; // Move Right
            }
        }

        return dp[m][n];
    }

    public static boolean canSum(int targetSum, int[] nums) {
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true;
        for (int i = 0; i <= targetSum; i++) {
            if (dp[i]) {
                for (int num : nums) {
                    if (i + num <= targetSum) {
                        dp[i + num] = true;
                    }
                }
            }
        }

        return dp[targetSum];
    }

    public static long howSum(long targetSum, int[] nums) {
        if (targetSum == 0)
            return 1;
        if (targetSum < 0)
            return 0;
        int[] dp = new int[(int) (targetSum + 1)];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = num; i <= targetSum; i++) {
                dp[i] += dp[i - num];
            }
        }
        return dp[(int) targetSum];
    }

    public static void main(String[] args) {
        System.out.println(howSum(7, new int[] { 5, 3, 2 }));
        System.out.println(howSum(300, new int[] { 7, 14 }));

    }
}
