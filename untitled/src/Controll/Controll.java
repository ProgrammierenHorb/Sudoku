package Controll;
import ADT.SudokuCell;
import Solver.Solver;
import Generator.Generator;

public class Controll {
    Solver SudokuSolver = new Solver();
    Generator SudokuGenerator = new Generator();
    SudokuCell[][] Grid = new SudokuCell[9][9];
    SudokuCell[][] SolvedGrid = new SudokuCell[9][9];
    public Controll(){

    }

    public void CallSudokuSolver(){
        SolvedGrid = SudokuSolver.solve(Grid);
    }
    public void CallSudokuGenerator(){
        SolvedGrid = SudokuGenerator.getSolvedGrid();
        Grid = SudokuGenerator.getGrid();
    }
}
