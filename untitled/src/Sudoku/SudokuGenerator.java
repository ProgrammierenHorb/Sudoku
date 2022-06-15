package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator {

    int solutions;
    SudokuCell[][] currentFilledGrid;
    List<Integer> array1to9;
    protected boolean kill = false;

    public SudokuGenerator() {

        array1to9 = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            array1to9.add(i + 1);
        }
    }

    public boolean possible(int n, int y, int x, SudokuCell[][] grid){

        int box_x;
        int box_y;

        for(int horizontal = 0; horizontal < 9; horizontal++){
            if(grid[y][horizontal].getIntValue() == n) return false;
        }

        for(int vertical = 0; vertical < 9; vertical++){
            if(grid[vertical][x].getIntValue() == n) return false;
        }

        box_x = (int) (x / 3) * 3;
        box_y = (int) (y / 3) * 3;

        for(int i = box_x; i < box_x + 3; i++){
            for(int j = box_y; j < box_y + 3; j++){
                if(grid[j][i].getIntValue() == n) return false;
            }
        }
        return true;
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

    public SudokuCell[][] fillGrid(SudokuCell[][] grid){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++) {
                if(grid[y][x].getIntValue() == 0){
                    Collections.shuffle(array1to9);
                    for(int n: array1to9){
                        if(possible(n, y, x, grid)) {
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
        solutions = 0;
        int rememberLastValue = 0;

        while(count > 0){

            int row = (int) (Math.random()*9);
            int column = (int) (Math.random()*9);

            if(grid[row][column].getIntValue() != 0){
                count--;
                rememberLastValue = grid[row][column].getIntValue();
                grid[row][column].setValue(0);
                grid[row][column].setLock(false);
                getNrofSolutions(grid);

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

    public int getNrofSolutions(SudokuCell[][] grid){
        solutions = 0;
        NrOfSolutions(grid);
        return solutions;
    }

    private SudokuCell[][] NrOfSolutions(SudokuCell[][] grid){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++) {
                if(grid[y][x].getIntValue() == 0){
                    for(int n = 1; n < 10; n++){
                        if(possible(n, y, x, grid)) {
                            grid[y][x].setValue(n);
                            NrOfSolutions(grid);
                            grid[y][x].setValue(0);
                        }
                    }
                    return grid;
                }
            }
        }
        solutions++;
        return grid;
    }

    public SudokuCell[][] getCurrentFilledGrid(){
        return currentFilledGrid;
    }

}