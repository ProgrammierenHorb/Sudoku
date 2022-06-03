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
        grid = sudokuSolver.solve(grid);
    }
    public SudokuCell[][] callSudokuGenerator(){
        grid = sudokuGenerator.getSolvedGrid();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].drawValueOnGUI();
                System.out.println(grid[i][j].getIntValue());
            }
        }
      //grid = sudokuGenerator.getSolvableGrid(solvedGrid);

        return grid;
    }
}
