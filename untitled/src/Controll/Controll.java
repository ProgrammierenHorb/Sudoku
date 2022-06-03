package Controll;
import ADT.SudokuCell;
import GUI.GUI;
import Solver.Solver;
import Generator.Generator;

public class Controll {
    private GUI theGUI;
    public Controll(){
        theGUI = new GUI(this);
    }
    public static void main(String[] args){
        Controll theControll = new Controll();
    }
    Solver sudokuSolver = new Solver();
    Generator sudokuGenerator = new Generator();
    SudokuCell[][] grid = new SudokuCell[9][9];
    SudokuCell[][] solvedGrid = new SudokuCell[9][9];
  

    public void callSudokuSolver(SudokuCell[][] grid){
        solvedGrid = sudokuSolver.solve(grid);
    }
    public SudokuCell[][] callSudokuGenerator(){
        solvedGrid = sudokuGenerator.getSolvedGrid();
        grid = sudokuGenerator.getSolvableGrid(solvedGrid);

        return grid;
    }
}
