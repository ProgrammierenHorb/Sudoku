package Generator;
import ADT.SudokuCell;
import Solver.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generator {
    Solver solver;
    int solutions;
    SudokuCell[][] currentFilledGrid;
    List<Integer> array1to9;

    public Generator() {
        solver = new Solver();
        array1to9 = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            array1to9.add(i + 1);
        }
    }

    public SudokuCell[][] generateFilledGrid(SudokuCell[][] grid){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].setValue(0);
            }
        }
        fillGrid(grid);
        currentFilledGrid = new SudokuCell[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                currentFilledGrid[i][j] = new SudokuCell();
                currentFilledGrid[i][j].setValue(grid[i][j].getIntValue());
            }
        }
        removeDigits(grid);
        kill = false;
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
                            grid[y][x].setLock(true);
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

    public void removeDigits(SudokuCell[][] grid){
        int[][] gridCopy = new int[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                gridCopy[i][j] = grid[i][j].getIntValue();
            }
        }

        int count = 65;
        //int[] rememberLastPosition = {0, 0};
        int solutions = 0;
        int rememberLastValue = 0;

        while(count > 0){

            int row = (int) (Math.random()*9);
            int column = (int) (Math.random()*9);

            if(grid[row][column].getIntValue() != 0){
                count--;
                rememberLastValue = grid[row][column].getIntValue();
                grid[row][column].setValue(0);
                grid[row][column].setLock(false);

                solutions = solver.getNrofSolutions(grid);

                if(solutions > 1 && count > 15){
                    count = 65;

                    for(int i = 0; i < 9; i++){
                        for(int j = 0; j < 9; j++){
                            grid[i][j].setValue(gridCopy[i][j]);
                            grid[i][j].setLock(true);
                        }
                    }

                }
                else if(solutions > 1){
                    grid[row][column].setValue(rememberLastValue);
                    grid[row][column].setLock(true);
                    break;
                }
            }

        }
    }

    public SudokuCell[][] getCurrentFilledGrid(){
        return currentFilledGrid;
    }

}