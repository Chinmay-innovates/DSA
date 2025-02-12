
package java_practice.pattern_matching;

import java.util.ArrayList;
import java.util.List;

// O(n + m) 
//n-> len(text) traversal, m->computing lps
public class KMP {
    private static int[] computeLPS(String pattern) {
        int m = pattern.length(), lps[] = new int[m];

        // Length of the previous LPS
        int len = 0, j = 1;

        while (j < m) {
            if (pattern.charAt(j) == pattern.charAt(len)) {
                len++;
                lps[j] = len;
                j++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[j] = 0;
                    j++;
                }
            }
        }
        return lps;
    }

    // Function to perform KMP pattern matching
    public static List<Integer> KMPSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        List<Integer> result = new ArrayList<>();

        // Edge case: Empty pattern or pattern longer than text
        if (m == 0 || n < m)
            return new ArrayList<>();

        int[] lps = computeLPS(pattern);
        int i = 0, j = 0; // i for text, j for pattern

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                // System.out.println("Pattern found at index " + (i - j));
                result.add(i - j);
                j = lps[j - 1]; // Move j to previous LPS value
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1]; // Use LPS array to skip comparisons
                } else {
                    i++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABDABABCABABABABDABACDABACABABABDABACDABABCABABABDABACDABABCABCABABABABDA";
        String pattern = "BABA";

        System.out.println("Pattern found at index " + KMPSearch(text, pattern));
    }
}
