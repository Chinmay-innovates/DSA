package java_practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class StringsPrac {
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        int[] freq = new int[26];
        int[] windFreq = new int[26];
        int winSize = s1.length();

        // Initialize frequency for s1 and first window of s2
        for (int i = 0; i < winSize; i++) {
            freq[s1.charAt(i) - 'a']++;
            windFreq[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(freq, windFreq))
            return true;

        for (int i = winSize; i < s2.length(); i++) {
            windFreq[s2.charAt(i) - 'a']++; // Add new character
            windFreq[s2.charAt(i - winSize) - 'a']--; // Remove old character

            if (Arrays.equals(freq, windFreq))
                return true;
        }
        return false;
    }

    // _ => small,capital,spChar
    public static boolean _checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        Map<Character, Integer> freq = new HashMap<>();
        Map<Character, Integer> winFreq = new HashMap<>();
        int winSize = s1.length();

        for (int i = 0; i < winSize; i++) {
            freq.put(s1.charAt(i), freq.getOrDefault(s1.charAt(i), 0) + 1);
            winFreq.put(s2.charAt(i), winFreq.getOrDefault(s2.charAt(i), 0) + 1);
        }

        if (freq.equals(winFreq))
            return true;

        for (int i = winSize; i < s2.length(); i++) {
            char newChar = s2.charAt(i);
            char oldChar = s2.charAt(i - winSize);

            winFreq.put(newChar, winFreq.getOrDefault(newChar, 0) + 1);

            if (winFreq.get(oldChar) == 1) {
                winFreq.remove(oldChar);
            } else {
                winFreq.put(oldChar, winFreq.get(oldChar) - 1);
            }

            if (freq.equals(winFreq))
                return true;
        }

        return false;
    }

    public static String reverseWords(String s) {
        int n = s.length();
        StringBuilder ans = new StringBuilder();
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        for (int i = 0; i < n; i++) {
            StringBuilder word = new StringBuilder();
            while (i < n && sb.charAt(i) != ' ') {
                word.append(sb.charAt(i));
                i++;
            }
            word.reverse();
            if (word.length() > 0) {
                ans.append(" ").append(word);
            }
        }
        return ans.length() > 0 ? ans.substring(1) : "";
    }

    public static String _reverseWords(String s) {
        String[] arr = s.trim().split(" ");
        StringBuilder word = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!arr[i].isEmpty()) {
                word.append(arr[i]);
                word.append(" ");
            }
        }
        return word.toString().trim();
    }

    public static char[] compress(char[] chars) {
        int n = chars.length, idx = 0, i = 0;

        while (i < n) {
            char ch = chars[i];
            int count = 0;

            while (i < n && chars[i] == ch) {
                count++;
                i++;
            }

            chars[idx++] = ch;
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[idx++] = c;
                }
            }
        }
        return Arrays.copyOf(chars, idx); // removes extra padding
    }

    public static void main(String[] args) {
        System.out.println(compress(new char[] { 'a', 'a', 'b', 'b', 'c', 'c', 'c' }));
        System.out.println(compress(new char[] { 'a' }));
        System.out.println(compress(new char[] { 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' }));
    }
}
