package java_practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// MEMOIZATION
public class Memoization {

    public static long partitions(int n, Map<Integer, Long> memo) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;

        if (memo.containsKey(n))
            return memo.get(n);

        long result = 0;
        int k = 1;

        while (true) {
            int pent1 = k * (3 * k - 1) / 2;
            int pent2 = k * (3 * k + 1) / 2;

            if (pent1 > n && pent2 > n)
                break;

            int sign = (k % 2 == 0) ? -1 : 1;

            if (pent1 <= n)
                result += sign * partitions(n - pent1, memo);
            if (pent2 <= n)
                result += sign * partitions(n - pent2, memo);
            k++;
        }

        memo.put(n, result);
        return result;
    }

    public static long fib(int n, Map<Integer, Long> memo) {
        if (memo.containsKey(n))
            return memo.get(n);
        if (n <= 2)
            return 1;
        long result = fib(n - 1, memo) + fib(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    public static long fact(int n, Map<Integer, Long> memo) {
        if (n <= 1)
            return 1;
        if (memo.containsKey(n))
            return memo.get(n);
        long result = n * fact(n - 1, memo);
        memo.put(n, result);
        return result;
    }

    public static long gridTraveller(int m, int n, Map<String, Long> memo) {
        if (m == 1 && n == 1)
            return 1;
        if (m == 0 || n == 0)
            return 0;

        String key = m + "," + n;
        if (memo.containsKey(key))
            return memo.get(key);

        long result = gridTraveller(m - 1, n, memo) + gridTraveller(m, n - 1, memo);
        memo.put(key, result);
        return result;
    }

    public static boolean canSum(int targetSum, int[] nums, Map<Integer, Boolean> memo) {
        if (memo.containsKey(targetSum))
            return memo.get(targetSum);
        if (targetSum == 0)
            return true;
        if (targetSum < 0)
            return false;

        for (int num : nums) {
            int remainder = targetSum - num;
            if (canSum(remainder, nums, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }

        memo.put(targetSum, false);
        return false;
    }

    public static List<Integer> howSum(int targetSum, int[] nums, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(targetSum))
            return memo.get(targetSum);
        if (targetSum == 0)
            return new ArrayList<>();
        if (targetSum < 0)
            return null;

        for (int num : nums) {
            int remainder = targetSum - num;
            List<Integer> remainderComb = howSum(remainder, nums, memo);
            if (remainderComb != null) {
                remainderComb.add(num);
                memo.put(targetSum, remainderComb);
                return remainderComb;
            }
        }

        memo.put(targetSum, null);
        return null;
    }

    public static List<Integer> bestSum(int targetSum, int[] nums, Map<Integer, List<Integer>> memo) {
        if (memo.containsKey(targetSum))
            return memo.get(targetSum);
        if (targetSum == 0)
            return new ArrayList<>();
        if (targetSum < 0)
            return null;

        List<Integer> shortestComb = null;
        for (int num : nums) {
            int remainder = targetSum - num;
            List<Integer> remainderComb = bestSum(remainder, nums, memo);

            if (remainderComb != null) {
                List<Integer> combination = new ArrayList<>(remainderComb);
                combination.add(num);

                if (shortestComb == null || combination.size() < shortestComb.size()) {
                    shortestComb = combination;
                }
            }
        }
        memo.put(targetSum, shortestComb);
        return shortestComb;

    }

    public static boolean canConstruct(String target, String[] wordBank, Map<Integer, Boolean> memo, int start) {
        if (memo.containsKey(start))
            return memo.get(start);
        if (start == target.length())
            return true;

        for (String word : wordBank) {
            if (target.startsWith(word, start)) {
                if (canConstruct(target, wordBank, memo, start + word.length())) {
                    memo.put(start, true);
                    return true;
                }
            }
        }
        memo.put(start, false);
        return false;
    }

    public static int countConstruct(String target, String[] wordBank, Map<Integer, Integer> memo, int start) {
        if (memo.containsKey(start))
            return memo.get(start);
        if (target.length() == start)
            return 1;

        int totalCount = 0;
        for (String word : wordBank) {
            if (start + word.length() <= target.length() && target.startsWith(word, start)) {
                int suffixes = countConstruct(target, wordBank, memo, start + word.length());
                totalCount += suffixes;
            }
        }
        memo.put(start, totalCount);
        return totalCount;
    }

    public static List<List<String>> allConstruct(String target, String[] wordBank,
            Map<String, List<List<String>>> memo) {
        if (memo.containsKey(target))
            return memo.get(target);
        if (target.isEmpty()) {
            List<List<String>> baseCase = new ArrayList<>();
            baseCase.add(new ArrayList<>());
            return baseCase;
        }

        List<List<String>> result = new ArrayList<>();
        for (String word : wordBank) {
            if (target.startsWith(word)) {
                String suffix = target.substring(word.length());
                List<List<String>> suffixWays = allConstruct(suffix, wordBank, memo);

                for (List<String> way : suffixWays) {
                    List<String> targetWays = new ArrayList<>(way);
                    targetWays.add(0, word);
                    result.add(targetWays);
                }
            }
        }
        memo.put(target, result);
        return result;
    }

    public static void allPossibleConstruct(String target, String[] wordBank, List<String> currentPath) {
        if (target.isEmpty()) {
            System.out.println(currentPath);
            return;
        }

        for (String word : wordBank) {
            if (target.startsWith(word)) {
                currentPath.add(word); // Add word to the path
                allPossibleConstruct(target.substring(word.length()), wordBank, currentPath);
                currentPath.remove(currentPath.size() - 1); // Backtrack
            }
        }
        /**
         * String target = "eeeeeeeeeeeeeeeeeeeeeeeeeee";
         * String[] wordBank = { "e", "ee", "eee", "eeee", "eeeee" };
         * 
         * allConstruct(target, wordBank, new ArrayList<>());
         */
    }

    public static int countConstruct(String target, String[] wordBank, Map<Integer, Integer> memo) {
        return countConstruct(target, wordBank, memo, 0);
    }

    public static boolean canConstruct(String target, String[] wordBank) {
        return canConstruct(target, wordBank, new HashMap<>(), 0);
    }

    // PRINT FUNCTIONS
    public static void printFact(int n) {
        System.out.println("Fact " + n + " : " + fact(n, new HashMap<>()));
    }

    public static void printFib(int n) {
        System.out.println("Fib " + n + " : " + fib(n, new HashMap<>()));
    }

    public static void printPart(int n) {
        System.out.println("P(" + n + ") : " + partitions(n, new HashMap<>()));
    }

    public static void printGrid(int m, int n) {
        System.out.println("Grid " + m + "," + n + " : " + gridTraveller(m, n, new HashMap<>()));
    }

    public static void printCanSum(int targetSum, int[] nums) {
        System.out.println("CanSum " + targetSum + " with " + Arrays.toString(nums) + " : "
                + canSum(targetSum, nums, new HashMap<>()));
    }

    public static void printHowSum(int targetSum, int[] nums) {
        List<Integer> result = howSum(targetSum, nums, new HashMap<>());
        System.out.println("HowSum " + targetSum + " with " + Arrays.toString(nums) + " : " + result);
    }

    public static void printBestSum(int targetSum, int[] nums) {
        List<Integer> result = bestSum(targetSum, nums, new HashMap<>());
        System.out.println("BestSum " + targetSum + " with " + Arrays.toString(nums) + " : " + result);
    }

    public static void printCanConstruct(String target, String[] wordBank) {
        System.out.println("CanConstruct " + target + " with " + Arrays.toString(wordBank) + " : "
                + canConstruct(target, wordBank, new HashMap<>(), 0));
    }

    public static void printAllConstruct(String target, String[] wordBank) {
        System.out.println("AllConstruct " + target + " with " + Arrays.toString(wordBank) + " : "
                + allConstruct(target, wordBank, new HashMap<>()));
    }

    public static void printCountConstruct(String target, String[] wordBank) {
        System.out.println("CountConstruct " + target + " with " + Arrays.toString(wordBank) + " : "
                + countConstruct(target, wordBank, new HashMap<>(), 0));
    }

    public static void main(String[] args) {
        long st = System.currentTimeMillis();

        printFib(20);
        printFib(50);

        printFact(18);
        printFact(20);

        printPart(50);
        printPart(200);

        printGrid(3, 3);
        printGrid(18, 18);

        printCanSum(33, new int[] { 5, 3, 4, 7 }); // true
        printCanSum(301, new int[] { 2, 14 }); // false

        printHowSum(7, new int[] { 5, 3, 4, 7 });
        printHowSum(300, new int[] { 7, 14 });

        // printBestSum(8, new int[] { 1, 4, 5 });
        // printBestSum(100, new int[] { 1, 2, 5, 25 });

        printAllConstruct("abcdef", new String[] { "ab", "abc", "cd", "def", "abcd" });
        printAllConstruct("skateboard", new String[] { "bo", "rd", "ate", "t", "ska", "sk", "boar" });
        printAllConstruct("", new String[] { "cat", "dog", "mouse" });
        printAllConstruct("enterapotentpot", new String[] { "a", "p", "ent", "enter", "ot", "o", "t" });
        printAllConstruct("purple",
                new String[] { "purp", "p", "ur", "le", "pur", "purple" });
        printAllConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeez",
                new String[] { "ee", "e", "eee", "eeeee", "eeeee", "eeeeee" });

        long et = System.currentTimeMillis();
        System.out.println("Execution Time: " + (et - st) + " ms");
    }
}
/**
 * import java.util.*;
 * 
 * public class Main {
 * public static Map<String, List<Integer>> memo = new HashMap<>();
 * 
 * public static List<Integer> bestSum(int targetSum, int[] nums, Map<Integer,
 * Integer> availableBills) {
 * String key = targetSum + ":" + availableBills; // Unique key based on
 * remaining bills
 * if (memo.containsKey(key)) return memo.get(key);
 * if (targetSum == 0) return new ArrayList<>();
 * if (targetSum < 0) return null;
 * 
 * List<Integer> shortestComb = null;
 * for (int num : nums) {
 * if (availableBills.get(num) > 0) { // Check if we have this denomination left
 * // Reduce the available count
 * availableBills.put(num, availableBills.get(num) - 1);
 * 
 * List<Integer> remainderComb = bestSum(targetSum - num, nums, availableBills);
 * 
 * if (remainderComb != null) {
 * List<Integer> combination = new ArrayList<>(remainderComb);
 * combination.add(num);
 * 
 * if (shortestComb == null || combination.size() < shortestComb.size()) {
 * shortestComb = combination;
 * }
 * }
 * // Restore the available count (backtracking)
 * availableBills.put(num, availableBills.get(num) + 1);
 * }
 * }
 * 
 * memo.put(key, shortestComb);
 * return shortestComb;
 * }
 * 
 * public static void main(String[] args) {
 * int targetSum = 230;
 * int[] nums = {100, 50, 20};
 * Map<Integer, Integer> availableBills = new HashMap<>();
 * availableBills.put(100, 2); // Only 2 bills of 100 available
 * availableBills.put(50, 3); // Only 3 bills of 50 available
 * availableBills.put(20, 4); // Only 4 bills of 20 available
 * 
 * List<Integer> result = bestSum(targetSum, nums, availableBills);
 * System.out.println(result); // Best way to make target sum within available
 * bills
 * }
 * }
 */