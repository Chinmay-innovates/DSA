package java_practice.practice;

import java.math.BigInteger;
import java.util.Arrays;

public class MathPractice {

    private final int THRESHOLD = 64;

    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] M = new int[n][n];

        if (n <= THRESHOLD) {
            return standardMultiply(A, B);
        }

        int half = n / 2;

        // Create submatrices manually
        int[][] A11 = new int[half][half];
        int[][] A12 = new int[half][half];
        int[][] A21 = new int[half][half];
        int[][] A22 = new int[half][half];

        int[][] B11 = new int[half][half];
        int[][] B12 = new int[half][half];
        int[][] B21 = new int[half][half];
        int[][] B22 = new int[half][half];

        // extracts the top-left of A
        split(A, A11, 0, 0);
        split(A, A12, 0, half);
        split(A, A21, half, 0);
        split(A, A22, half, half);

        // extracts the top-left of B
        split(B, B11, 0, 0);
        split(B, B12, 0, half);
        split(B, B21, half, 0);
        split(B, B22, half, half);

        // Corrected multiply calls
        int[][] P = multiply(add(A11, A22), add(B11, B22));
        int[][] Q = multiply(add(A21, A22), B11);
        int[][] R = multiply(A11, sub(B12, B22));
        int[][] S = multiply(A22, sub(B21, B11));
        int[][] T = multiply(add(A11, A12), B22);
        int[][] U = multiply(sub(A21, A11), add(B11, B12));
        int[][] V = multiply(sub(A12, A22), add(B21, B22));

        int[][] C11 = add(sub(add(P, S), T), V);
        int[][] C12 = add(R, T);
        int[][] C21 = add(Q, S);
        int[][] C22 = add(sub(add(P, R), Q), U);

        // pastes C12 into M.
        join(C11, M, 0, 0);
        join(C12, M, 0, half);
        join(C21, M, half, 0);
        join(C22, M, half, half);

        return M;
    }

    private int[][] standardMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    private int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    private void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    private void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }

    private void printMatrix(int[][] C) {
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++)
                System.out.print(C[i][j] + " ");
            System.out.println();
        }
    }

    public BigInteger karatsuba(BigInteger x, BigInteger y) {
        int n = Math.max(x.bitLength(), y.bitLength());

        if (n <= 2000) {
            return x.multiply(y);
        }

        n = (n / 2) + (n % 2);

        // Split numbers into left and right halves
        BigInteger A = x.shiftRight(n);
        BigInteger B = x.subtract(A.shiftLeft(n));
        BigInteger C = y.shiftRight(n);
        BigInteger D = y.subtract(C.shiftLeft(n));

        // Recursive steps
        BigInteger AC = karatsuba(A, C); // X_L * Y_L
        BigInteger BD = karatsuba(B, D); // X_R * Y_R
        BigInteger AC_plus_BD = karatsuba(A.add(B), C.add(D)); // (X_L + X_R) * (Y_L + Y_R)

        // Compute result using Karatsuba's formula
        return AC.shiftLeft(2 * n).add(AC_plus_BD.subtract(AC).subtract(BD).shiftLeft(n)).add(BD);
    }

    // O(n log log n)
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        int count = 0;

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
                // System.out.print(i + " ");
                for (int j = i * 2; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MathPractice matrix = new MathPractice();
        MathPractice ks = new MathPractice();

        int[][] A = {
                { 34, 12, 78, 90, 56, 32, 45, 99, 23, 11, },
                { 56, 89, 23, 67, 12, 45, 78, 90, 43, 21, },
                { 56, 89, 23, 67, 12, 45, 78, 90, 43, 21, },
                { 34, 12, 78, 90, 56, 32, 45, 99, 23, 11, },
                { 87, 65, 43, 23, 56, 78, 12, 90, 67, 45, },
                { 34, 12, 78, 90, 56, 32, 45, 99, 23, 11, },
                { 34, 12, 78, 90, 56, 32, 45, 99, 23, 11, },
                { 87, 65, 43, 23, 56, 78, 12, 90, 67, 45, },
                { 34, 12, 78, 90, 56, 32, 45, 99, 23, 11, },
                { 34, 12, 78, 90, 56, 32, 45, 99, 23, 11, },
        };

        int[][] B = {
                { 34, 12, 78, 90, 56, 32, 45, 32, 23, 11, },
                { 56, 89, 23, 67, 12, 45, 78, 45, 43, 21, },
                { 34, 12, 78, 90, 56, 32, 45, 32, 23, 11, },
                { 87, 65, 43, 23, 56, 78, 12, 78, 67, 45, },
                { 87, 65, 43, 23, 56, 78, 12, 78, 67, 45, },
                { 56, 89, 23, 67, 12, 45, 78, 45, 43, 21, },
                { 34, 12, 78, 90, 56, 32, 45, 32, 23, 11, },
                { 34, 12, 78, 90, 56, 32, 45, 32, 23, 11, },
                { 34, 12, 78, 90, 56, 32, 45, 32, 23, 11, },
                { 34, 12, 78, 90, 56, 32, 45, 32, 23, 11, },
        };

        BigInteger num1 = new BigInteger("31415926535897932384626271828182845904523536028747375105820974944592");
        BigInteger num2 = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");

        long st = System.nanoTime();
        int[][] C = matrix.multiply(A, B);

        System.out.println(ks.karatsuba(num1, num2));

        System.out.println(matrix.countPrimes(10));
        System.out.println(matrix.countPrimes(32523));
        long et = System.nanoTime();

        matrix.printMatrix(C);
        System.out.println("Time taken: " + (et - st) + " ns");

    }
}
