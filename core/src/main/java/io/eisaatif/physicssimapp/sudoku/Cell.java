package io.eisaatif.physicssimapp.sudoku;

import java.util.ArrayList;

public class Cell {

    private ArrayList<Integer> solutions = new ArrayList<>();
    private int[][] sudoku;
    public int i;
    public int j;

    public Cell(int i, int j, ArrayList<Integer> solutions, int[][] sudoku) {
        this.i = i; this.j = j;
        this.solutions = solutions;
        this.sudoku = sudoku;
    }

    public Cell(ArrayList<Integer> solutions, int[][] sudoku) {
        this.solutions = solutions;
        this.sudoku = sudoku;
    }

    public int getSolution() {
        if(solutions.size() == 1) {
            return solutions.get(0);
        } else {
            return -1;
        }
    }

    public ArrayList<Integer> getSolutions() {
        return solutions;
    }

    public void setSolutions(ArrayList<Integer> solutions) {
        this.solutions = solutions;
    }

}
