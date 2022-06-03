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
    public SudokuCell[][] callSudokuGenerator(SudokuCell[][] grid){
        sudokuGenerator.getSolvedGrid(grid);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].drawValueOnGUI();
            }
        }

        return grid;
    }
}
