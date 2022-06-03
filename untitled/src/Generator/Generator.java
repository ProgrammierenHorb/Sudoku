package Generator;
import ADT.SudokuCell;
import Solver.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {
    Solver solver = new Solver();
    SudokuCell[][] testgrid = new SudokuCell[9][9];
    List<Integer> array1to9 = new ArrayList<>();

    public Generator(){
        for(int i = 0 ; i<9; i++) {
            array1to9.add(i+1);
            for (int j = 0; j < 9; j++) {
                testgrid[i][j] = new SudokuCell(0,false);
            }
        }

    }
    public SudokuCell[][] getSolvedGrid(){
        SudokuCell[][] solvedGrid = new SudokuCell[9][9];
        for(int i = 0 ; i<9; i++){
            for(int j = 0 ; j<9; j++){
                solvedGrid[i][j] = new SudokuCell(0,false);
            }
        }

        return solvedGrid;
    }

    public SudokuCell[][] getSolvableGrid(SudokuCell[][] FullGrid){
        SudokuCell[][] grid = new SudokuCell[9][9];
        fillGrid(grid);
        return grid;
    }

    boolean kill = false;
    public SudokuCell[][] fillGrid(SudokuCell[][] grid){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++) {
                if(grid[y][x].getIntValue() == 0){
                    Collections.shuffle(array1to9);
                    for(int n: array1to9){
                        if(solver.possible(n, y, x, grid)) {
                            grid[y][x].setValue(n);
                            fillGrid(grid);
                            if(!kill){
                                grid[y][x].setValue(0);
                            }
                        }
                    }
                    return grid;
                }
            }
        }
        kill = true;
        return grid;
    }
}