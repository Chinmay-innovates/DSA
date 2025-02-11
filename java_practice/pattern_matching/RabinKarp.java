package java_practice.pattern_matching;

/**
 * Best Case & Average Case:
 * 𝑂(𝑁/𝑀) If hashing is efficient and there are no collisions
 * 
 * Worst Case:
 * 𝑂(𝑁𝑀) When all characters match except the last one repeatedly
 */
public class RabinKarp {
    private static final int PRIME = 1_000_000_007; // _or_ 101
    private static final int BASE = 256;

    // Rabin-Karp pattern search function
    public static void rabinKarpSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        int textHash = 0, patternHash = 0, h = 1;

        // Compute h = (base^(m-1)) % PRIME
        for (int i = 0; i < m - 1; i++) {
            h = (h * BASE) % PRIME;
        }

        // Compute initial hash values for pattern and first window of text
        for (int i = 0; i < m; i++) {
            patternHash = (BASE * patternHash + pattern.charAt(i)) % PRIME;
            textHash = (BASE * textHash + text.charAt(i)) % PRIME;
        }

        // Slide the pattern over the text
        for (int i = 0; i <= n - m; i++) {
            // If hash values match, check character by character
            if (patternHash == textHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Compute next window's hash (rolling hash)
            if (i < n - m) {
                textHash = (BASE * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;
                if (textHash < 0) {
                    textHash += PRIME; // Ensure hash is positive
                }
            }
        }
    }

    /**
     * Overall Complexity:
     * 𝑂(𝑁+log𝑀) If hashing is efficient and there are no collisions
     * 
     * Preprocessing O(log𝑀)
     * Hash Computation O(𝑀)
     * Pattern Matching O(𝑁)
     */
    public static void optimizedRabinKarpSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        int textHash = 0, patternHash = 0;
        int h = (int) powMod(BASE, m - 1, PRIME); // Modular exponentiation

        for (int i = 0; i < m; i++) {
            patternHash = (int) ((BASE * (long) patternHash + pattern.charAt(i)) % PRIME);
            textHash = (int) ((BASE * (long) textHash + text.charAt(i)) % PRIME);
        }

        // Slide the pattern over the text
        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // next window's hash using rolling hash technique
            if (i < n - m) {
                textHash = (int) ((BASE * (long) (textHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME);
                if (textHash < 0) {
                    textHash += PRIME; // Ensure hash is positive
                }
            }
        }
    }

    private static long powMod(long base, long exp, long mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) { // If exponent is odd
                result = (result * base) % mod;
            }
            base = (base * base) % mod; // Square base
            exp >>= 1; // Divide exponent by 2
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABDABABCABABABABDABACDABACABABABDABACDABABCABABABDABACDABABCABCABABABABDA";
        String pattern = "BABA";
        rabinKarpSearch(text, pattern);
        optimizedRabinKarpSearch(text, pattern);
    }
}