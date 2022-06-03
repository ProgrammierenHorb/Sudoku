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

    Solver SudokuSolver = new Solver();
    Generator SudokuGenerator = new Generator();
    SudokuCell[][] Grid = new SudokuCell[9][9];
    SudokuCell[][] SolvedGrid = new SudokuCell[9][9];

    public void CallSudokuSolver(){
        SolvedGrid = SudokuSolver.solve(Grid);
    }
    public void CallSudokuGenerator(){
        SolvedGrid = SudokuGenerator.GetSolvedGrid();
        Grid = SudokuGenerator.GetSolvableGrid(SolvedGrid);
    }
}
