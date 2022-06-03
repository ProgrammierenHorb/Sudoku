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

    public void callSudokuSolver(SudokuCell[][] grid){
        grid = sudokuSolver.solve(grid);
    }
    public void callSudokuGenerator(SudokuCell[][] grid){
        sudokuGenerator.getSolvedGrid(grid);

        
    }
}
