package java_practice.practice;


import java.util.ArrayList;
import java.util.HashSet;

public class Recursion {

    private static String str = "cos";
    private static int n = 3;
    private static int m = 3;
    private static ArrayList<Integer> subset = new ArrayList<>();
    private static int first = -1;
    private static int last = -1;
    private static int arr[] = { 1, 2, 3 };
    private static String abxccdff = "abxccdff";
    private static boolean[] map = new boolean[26];
    private static HashSet<String> set = new HashSet<>();
    private static String[] keypad = { ".", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void printPermutation(String string, String permutation) {
        if (string.length() == 0) {
            System.out.println(permutation);
            return;
        }
        for (int i = 0; i < string.length(); i++) {
            char currentCharecter = string.charAt(i);
            // "abc"-> "ab"
            String newString = string.substring(0, i) + string.substring(i + 1);
            printPermutation(newString, permutation + currentCharecter);
        }
    }

    public static int countPaths(int i, int j, int n, int m) {
        if (i == n && j == m) {
            return 0;
        }
        if (i == n - 1 && j == m - 1) {
            return 1;
        }
        int downwardPaths = countPaths(i + 1, j, n, m);
        int rightwardPaths = countPaths(i, j + 1, n, m);
        return downwardPaths + rightwardPaths;
    }

    public static int placeTiles(int n, int m) {

        if (n == m) {
            return 2;
        }
        if (n < m) {
            return 1;
        }

        // place vertically
        int verticalPlacements = placeTiles(n - m, m);
        // place horizontally
        int horizontalPlacements = placeTiles(n - 1, m);

        return verticalPlacements + horizontalPlacements;

    }

    public static int callGuests(int n) {
        if (n <= 1) {
            return 1;
        }
        int callSingle = callGuests(n - 1);
        int callPair = (n - 1) * callGuests(n - 2);

        return callSingle + callPair;
    }

    public static void findSubsets(int n, ArrayList<Integer> subset) {

        if (n == 0) {
            printSubset(subset);
            return;
        }
        // can add
        subset.add(n);
        findSubsets(n - 1, subset);

        // cannot add
        subset.remove(subset.size() - 1);
        findSubsets(n - 1, subset);

    }

    public static void printSubset(ArrayList<Integer> subset) {
        for (int i = 0; i < subset.size(); i++) {
            System.out.print(subset.get(i) + " ");
        }
        System.out.println();
    }

    public static void findOccurence(String string, int index, char element) {

        if (index == string.length()) {
            System.out.println("first occurence: " + first);
            System.out.println("last occurence: " + last);
            return;
        }
        char currChar = string.charAt(index);
        if (currChar == element) {
            if (first == -1) {
                first = index;
            } else {
                last = index;
            }
        }
        findOccurence(string, index + 1, element);
    }

    public static void printReverse(String string, int index) {

        if (index == 0) {
            System.out.println(string.charAt(index));
            return;
        }

        System.out.print(string.charAt(index));
        printReverse(string, index - 1);
    }

    public static boolean isSorted(int arr[], int index) {
        // strictly increasing
        if (index == arr.length - 1) {
            return true;
        }

        if (arr[index] >= arr[index + 1]) {
            // array is unsorted
            return false;
        }
        return isSorted(arr, index + 1);
    }

    public static void moveAllX(String str, char move, int idx, int count, String newString) {

        if (idx == str.length()) {
            for (int i = 0; i < count; i++) {
                newString += move;
            }
            System.out.println(newString);
            return;
        }

        char currChar = str.charAt(idx);
        if (currChar == move) {
            count++;
            moveAllX(str, move, idx + 1, count, newString);
        } else {
            newString += currChar;
            moveAllX(str, move, idx + 1, count, newString);
        }
    }

    public static void removeDuplicates(String str, int idx, String newString) {

        if (idx == str.length()) {
            System.out.println(newString);
            return;
        }
        char currChar = str.charAt(idx);
        if (map[currChar - 'a']) {
            removeDuplicates(str, idx + 1, newString);
        } else {
            newString += currChar;
            map[currChar - 'a'] = true;
            removeDuplicates(str, idx + 1, newString);

        }
    }

    public static void subSequences(String str, int idx, String newString,
            HashSet<String> set) {

        if (idx == str.length()) {
            if (set.contains(newString)) {
                return;
            } else {
                System.out.println(newString);
                set.add(newString);
                return;
            }
        }

        char currChar = str.charAt(idx);

        // to be
        subSequences(str, idx + 1, newString + currChar, set);

        // or not to be
        subSequences(str, idx + 1, newString, set);
    }

    public static void printCombinations(String str, int idx, String combination) {

        if (idx == str.length()) {
            System.out.println(combination);
            return;
        }

        char currChar = str.charAt(idx);
        String mapping = keypad[currChar - '0'];

        for (int i = 0; i < mapping.length(); i++) {
            printCombinations(str, idx + 1, combination + mapping.charAt(i));
        }
    }

    public static void towerOfHanoi(int n, String source, String helper, String destination) {
        if (n == 1) {
            System.out.println("transfer disk " + n + " from " + source + " to " + destination);
            return;
        }

        towerOfHanoi(n - 1, source, destination, helper);
        System.out.println("transfer disk " + n + " from " + source + " to " + destination);
        towerOfHanoi(n - 1, helper, source, destination);
    }

    public static void main(String[] args) {

        // get permutations
        System.out.println("Total permutations for given string " + str + " : " + factorial(str.length()));
        printPermutation(str, "");

        // get total paths from 0,0 to n,m in a n*m matrix
        int totalPaths = countPaths(0, 0, n, m);
        System.out.println(totalPaths);

        // get no.of tiles of size 1*m in a floor of size n*m
        System.out.println("Total placements : " + placeTiles(n, m));

        // get no of invites to your party
        System.out.println("Total wasys to call : " + callGuests(n));

        // get subsets of given string
        System.out.println("subsets of n = " + n);
        findSubsets(n, subset);

        // ----------------------------------------------------------//

        towerOfHanoi(n, "S", "H", "D");

        printReverse(str, str.length() - 1);

        findOccurence(abxccdff, 0, 'f');

        System.out.println(isSorted(arr, 0));

        moveAllX(abxccdff, 'c', 0, 0, "");

        removeDuplicates(abxccdff, 0, "");

        subSequences("aaa", 0, "", set);

        printCombinations("24", 0, "");

    }

}