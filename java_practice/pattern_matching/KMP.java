package java_practice.pattern_matching;

// O(n + m) 
//n-> len(text) traversal, m->computing lps
public class KMP {
    // Compute the Longest Prefix Suffix (LPS) array
    private static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = ++len;
            } else if (len != 0) {
                len = lps[len - 1]; // Fallback to the previous LPS value
            } else {
                lps[i++] = 0; // No match, reset LPS to 0
            }
        }
        return lps;
    }

    // Function to perform KMP pattern matching
    public static void KMPSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();

        // Edge case: Empty pattern or pattern longer than text
        if (m == 0 || n < m)
            return;

        int[] lps = computeLPS(pattern);
        int i = 0, j = 0; // i for text, j for pattern

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                // If full pattern is found
                if (j == m) {
                    System.out.println("Pattern found at index " + (i - j));
                    j = lps[j - 1]; // Reset j to the previous LPS value
                }
            } else {
                j = (j != 0) ? lps[j - 1] : j + 1;
                if (j == 0)
                    i++; // Only move i when j resets
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABA";
        KMPSearch(text, pattern);
    }
}
