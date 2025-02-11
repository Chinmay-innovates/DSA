package java_practice.pattern_matching;

import java.util.Arrays;

/**
 * Best Case:
 * 𝑂(𝑁/𝑀) If mismatches occur early
 * 
 * Worst Case:
 * 𝑂(𝑁𝑀) When all characters match except the last one repeatedly
 * 
 * Average Case:
 * Better than KMP, especially for large alphabets.
 */
public class BoyerMoore {
    private static final int ALPHABET_SIZE = 256;

    private static int[] computeBadMatchTable(String pattern) {
        int m = pattern.length();
        int[] badMatch = new int[ALPHABET_SIZE];

        Arrays.fill(badMatch, -1);

        // Fill the actual values of last occ. of characters in the pattern
        for (int i = 0; i < m; i++) {
            badMatch[pattern.charAt(i)] = i;
        }

        return badMatch;
    }

    public static void boyerMooreSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] badMatch = computeBadMatchTable(pattern);

        int shift = 0; // w.r.t text

        while (shift <= (n - m)) {
            int j = m - 1;

            // Move from right to left in the pattern
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            if (j < 0) {
                System.out.println("Pattern found at index " + shift);
                shift += (shift + m < n) ? m - badMatch[text.charAt(shift + m)] : 1;
            } else {
                shift += Math.max(1, j - badMatch[text.charAt(shift + j)]);
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABDABABCABABABABDABACDABACABABABDABACDABABCABABABDABACDABABCABCABABABABDA";
        String pattern = "BABA";
        boyerMooreSearch(text, pattern);
    }

}
