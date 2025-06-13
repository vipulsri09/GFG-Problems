import java.util.Arrays;

class Solution {
    static boolean[][] rows = new boolean[9][10];
    static boolean[][] cols = new boolean[9][10];
    static boolean[][] boxes = new boolean[9][10];

    static void solveSudoku(int[][] mat) {
        for (int i = 0; i < 9; i++) {
            Arrays.fill(rows[i], false);
            Arrays.fill(cols[i], false);
            Arrays.fill(boxes[i], false);
        }
        valid(mat);
        solve(mat, 0);
    }

    static void valid(int[][] mat) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = mat[i][j];
                if (val != 0) {
                    rows[i][val] = true;
                    cols[j][val] = true;
                    boxes[boxIndex(i, j)][val] = true;
                }
            }
        }
    }

    static boolean solve(int[][] mat, int cell) {
        if (cell == 81) return true;
        int row = cell / 9;
        int col = cell % 9;

        if (mat[row][col] != 0) {
            return solve(mat, cell + 1);
        }

        for (int num = 1; num <= 9; num++) {
            int box = boxIndex(row, col);
            if (!rows[row][num] && !cols[col][num] && !boxes[box][num]) {
                mat[row][col] = num;
                rows[row][num] = cols[col][num] = boxes[box][num] = true;

                if (solve(mat, cell + 1)) return true;

                mat[row][col] = 0;
                rows[row][num] = cols[col][num] = boxes[box][num] = false;
            }
        }

        return false;
    }

    static int boxIndex(int row, int col) {
        return (row / 3) * 3 + (col / 3);
    }
}
