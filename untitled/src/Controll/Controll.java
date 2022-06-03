package Controll;
import ADT.SudokuCell;
import Solver.Solver;
import Generator.Generator;

public class Controll {
    Solver sudokuSolver = new Solver();
    Generator sudokuGenerator = new Generator();
    SudokuCell[][] grid = new SudokuCell[9][9];
    SudokuCell[][] solvedGrid = new SudokuCell[9][9];
    public Controll(){

    }

    public void callSudokuSolver(){
        solvedGrid = sudokuSolver.solve(grid);
    }
    public void callSudokuGenerator(){
        solvedGrid = sudokuGenerator.getSolvedGrid();
        grid = sudokuGenerator.getSolvableGrid(solvedGrid);
    }
}
