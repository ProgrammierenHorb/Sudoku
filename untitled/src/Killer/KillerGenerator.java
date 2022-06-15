package Killer;

import Sudoku.SudokuCell;
import Sudoku.SudokuGenerator;

public class KillerGenerator extends SudokuGenerator {

    private KillerCell[][] currentFilledGrid;

    public KillerGenerator(){
        super();
    }

    public SudokuCell[][] generateFilledGrid(SudokuCell[][] grid){

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].setValue(0);
            }
        }

        fillGrid(grid);

        currentFilledGrid = new KillerCell[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                currentFilledGrid[i][j] = new KillerCell();
                currentFilledGrid[i][j].setValue(grid[i][j].getIntValue());
            }
        }
        removeDigits(grid);
        kill = false;
        return grid;
    }
}
