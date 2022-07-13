package Controll;

import Sudoku.SudokuCell;

public abstract class Generator {

    protected int[][] currentFilledGrid;

    public abstract boolean possible(int n, int y, int x, Cell[][] grid);
    public abstract void generateFilledGrid(Cell[][] grid, String difficulty);
    protected abstract Cell[][] fillGrid(Cell[][] grid);
    protected abstract void removeValues(Cell[][] grid, String difficulty);
    public abstract int getNrOfSolutions(Cell[][] grid);
    protected abstract Cell[][] nrOfSolutions(Cell[][] grid);
    public int[][] getCurrentFilledGrid() {
        return currentFilledGrid;
    }

}
