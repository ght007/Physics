package io.eisaatif.physicssimapp;

import java.util.ArrayList;

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
        int[][] solved = new int[sudoku.length][sudoku[0].length];
        /*
        FORMAT: n = 9 with sub-boxes of Math.sqrt(9)xMath.sqrt(9)
        sudoku[0] = [(0, 0, 3), (0, 2, 0), (6, 0, 0)]
        sudoku[1] = [(9, 0, 0), (3, 0, 5), (0, 0, 1)]
        sudoku[2] = [(0, 0, 1), (8, 0, 6), (4, 0, 0)]
         */
        for(int i = 0; i < solved.length; i++) {
            for(int j = 0; j < solved[0].length; j++) {

            }
        }

        return solved;
    }

    private int solutionToCell(int i, int j) {
        ArrayList<Integer> sols = new ArrayList<>();
        if(sudoku[i][j] == 0) {
            // Test for row
            for(int k = 1; k <= sudoku.length; k++) {
                if(!sols.contains(k) && sudoku[i][k] != 0 && sudoku[i][k] != k) {
                    sols.add(k);
                }
            }

            // Test for column
            for(int k = 1; k <= sudoku[0].length; k++) {
                if(!sols.contains(k) && sudoku[k][j] != 0 && sudoku[k][j] != k) {
                    sols.add(k);
                }
            }

            // Test for box
            // TODO add classes for individual cells to store possible solutions (Matrix of NxN cell objects)
        }
        return -1;
    }
}
