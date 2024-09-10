package io.eisaatif.physicssimapp.sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class SudokuSolver {
    int[][] sudoku;

    public SudokuSolver(int[][] sudoku) {
        for (int[] row : sudoku) {
            if (row.length != sudoku.length) {
                throw new IllegalArgumentException("Sudoku does not have the same length");
            }
        }
        this.sudoku = sudoku;
    }

    public int[][] solve() {
        /*
        FORMAT: n = 9 with sub-boxes of Math.sqrt(9)xMath.sqrt(9)
        sudoku[0] = [(0, 0, 3), (0, 2, 0), (6, 0, 0)]
        sudoku[1] = [(9, 0, 0), (3, 0, 5), (0, 0, 1)]
        sudoku[2] = [(0, 0, 1), (8, 0, 6), (4, 0, 0)]
         */
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku[0].length; j++) {
                    sudoku[i][j] = (solutionToCell(i, j) != -1) ? solutionToCell(i, j) : sudoku[i][j];
                }
            }
        }

        return sudoku;
    }

    public int solutionToCell(int i, int j) {
        Cell cell = new Cell(i, j, new ArrayList<Integer>(), sudoku);
        ArrayList<Integer> solsRow = new ArrayList<>();
        ArrayList<Integer> solsCol = new ArrayList<>();
        ArrayList<Integer> solsBox = new ArrayList<>();

        if (sudoku[i][j] == 0) {
            // Test for row
            for (int k = 1; k <= sudoku.length; k++) {
                if (!solsRow.contains(k) && !contains(sudoku[i], k)) {
                    solsRow.add(k);
                }
            }

            // Test for column
            int[] col = new int[sudoku[0].length];
            for (int k = 0; k < sudoku[0].length; k++) {
                col[k] = sudoku[k][j];
            }

            for (int k = 1; k <= sudoku[0].length; k++) {
                if (!solsCol.contains(k) && !contains(col, k)) {
                    solsCol.add(k);
                }
            }

            // Test for box
            int[] box = new int[sudoku.length];
            for (int k = 0; k < (int) Math.sqrt(sudoku.length); k++) {
                for (int n = 0; n < (int) Math.sqrt(sudoku.length); n++) {
                    // n + 3*k for adjusting that 0 <= k <= sqrt(len) and not len
                    box[n + 3 * k] = sudoku[i - (i % (int) Math.sqrt(sudoku.length)) + k][j - (j % (int) Math.sqrt(sudoku.length)) + n];
                }
            }

            for (int k = 1; k <= sudoku.length; k++) {
                if (!solsBox.contains(k) && !contains(box, k)) {
                    solsBox.add(k);
                }
            }
        }

        cell.setSolutions(intersect(solsRow, solsCol, solsBox));
        return cell.getSolution();
    }

    private static boolean contains(int[] arr, int n) {
        ArrayList<Integer> x = new ArrayList<>();
        for (int a : arr) {
            x.add(a);
        }
        return x.contains(n);
    }

    private static ArrayList<Integer> intersect(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> c) {
        ArrayList<Integer> res = new ArrayList<>(a);
        res.retainAll(b);
        res.retainAll(c);
        return res;
    }
}

