package java_practice.pattern_matching;

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
    public static void KMPSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        int[] lps = computeLPS(pattern);

        int i = 0, j = 0; // i for text, j for pattern

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1]; // Move j to previous LPS value
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1]; // Use LPS array to skip comparisons
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABDABABCABABABABDABACDABACABABABDABACDABABCABABABDABACDABABCABCABABABABDA";
        String pattern = "BABA";
        KMPSearch(text, pattern);
    }
}
