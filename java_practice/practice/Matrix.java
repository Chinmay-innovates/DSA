package java_practice.practice;

import java.util.*;

public class Matrix {

    class Pair {
        int row, col;

        public Pair(int r, int c) {
            this.row = r;
            this.col = c;
        }

        public void add(int r, int c) {
            this.row += r;
            this.col += c;
        }

        @Override
        public String toString() {
            return '[' + "row=" + row + ", col=" + col + ']';
        }

    }

    public boolean linearSearch(int[][] mat, int target) {
        for (int[] row : mat)
            for (int col : row)
                if (col == target)
                    return true;

        return false;
    }

    public List<Pair> getIndex(int[][] mat, int target) {
        List<Pair> result = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == target) {
                    result.add(new Pair(i, j));
                }
            }
        }
        return !result.isEmpty() ? result : List.of(new Pair(-1, -1));
    }

    public int maxRowSum(int[][] mat) {
        int maxRowSum = Integer.MIN_VALUE;

        for (int i = 0; i < mat.length; i++) {
            int rowSumI = 0;
            for (int j = 0; j < mat[0].length; j++) {
                rowSumI += mat[i][j];
            }
            maxRowSum = Math.max(rowSumI, maxRowSum);
        }
        return maxRowSum;
    }

    public int maxColSum(int[][] mat) {
        int maxColSum = Integer.MIN_VALUE;

        for (int j = 0; j < mat[0].length; j++) {
            int colSumJ = 0;
            for (int i = 0; i < mat.length; i++) {
                colSumJ += mat[i][j];
            }
            maxColSum = Math.max(colSumJ, maxColSum);
        }
        return maxColSum;
    }

    public int diagonalSum(int[][] mat) {
        int sum = 0, n = mat.length;

        for (int i = 0; i < n; i++) {
            sum += mat[i][i]; // Primary diagonal
            if (i != n - i - 1) {
                sum += mat[i][n - i - 1]; // Secondary diagonal
            }
        }
        return sum;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length; // m = rows, n = cols
        int startRow = 0, endRow = m - 1;

        while (startRow <= endRow) {
            int midRow = startRow + (endRow - startRow) / 2;

            if (target >= matrix[midRow][0] && target <= matrix[midRow][n - 1])
                return searchInRow(matrix, midRow, target);
            else if (target > matrix[midRow][n - 1])
                startRow = midRow + 1;
            else
                endRow = midRow - 1;

        }
        return false;
    }

    private boolean searchInRow(int[][] matrix, int row, int target) {
        int n = matrix[0].length;
        int st = 0, end = n - 1;

        while (st <= end) {
            int mid = st + (end - st) / 2;
            if (target == matrix[row][mid])
                return true;
            else if (target > matrix[row][mid])
                st = mid + 1;
            else
                end = mid - 1;
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length; // m = rows, n = cols
        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (matrix[r][c] == target)
                return true;
            else if (matrix[r][c] > target)
                c--;
            else
                r++;
        }
        return false;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length; // m = rows, n = cols
        int stRow = 0, endRow = m - 1, stCol = 0, endCol = n - 1;

        while (stRow <= endRow && stCol <= endCol) { // '<=' for odd-sized matrix
            // Traverse from left to right
            for (int j = stCol; j <= endCol; j++)
                spiral.add(matrix[stRow][j]);

            // Traverse from top to bottom
            for (int i = stRow + 1; i <= endRow; i++)
                spiral.add(matrix[i][endCol]);

            // Traverse from right to left
            if (stRow < endRow) {
                for (int j = endCol - 1; j >= stCol; j--)
                    spiral.add(matrix[endRow][j]);
            }

            // Traverse from bottom to top
            if (stCol < endCol) {
                for (int i = endRow; i > stRow; i--)
                    spiral.add(matrix[i][stCol]);
            }

            stRow++;
            endRow--;
            stCol++;
            endCol--;
        }
        return spiral;
    }

    public static void main(String[] args) {

        int[][] mat = {
                { 1, 2, 4 },
                { 4, 5, 6 },
                { 7, 8, 9 },
        };
        int[][] mat2 = {
                { 10, 11, 16, 20 },
                { 12, 25, 27, 35 },
                { 32, 32, 34, 27 },
                { 41, 60, 62, 80 },
        };

        int[][] mat3 = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 },
        };
        System.out.println(new Matrix().getIndex(mat, 2));
        System.out.println(new Matrix().getIndex(mat, 19));

        System.out.println(new Matrix().maxRowSum(mat));

        System.out.println(new Matrix().maxColSum(mat));

        System.out.println(new Matrix().diagonalSum(mat));
        System.out.println(new Matrix().diagonalSum(mat3));

        System.out.println(new Matrix().searchMatrix(mat3, 13));
        System.out.println(new Matrix().searchMatrix(mat3, 17));

        System.out.println(new Matrix().searchMatrix2(mat2, 25));
        System.out.println(new Matrix().searchMatrix2(mat2, 62));
        System.out.println(new Matrix().searchMatrix2(mat2, 34));
        System.out.println(new Matrix().searchMatrix2(mat2, 1));

        System.out.println(new Matrix().spiralOrder(mat));

    }
}
