package Sudoku;

import Controll.Generator;
import Controll.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuGenerator extends Generator {

    int solutions;
    List<Integer> array1to9;
    protected boolean kill = false;

    public SudokuGenerator() {

        array1to9 = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            array1to9.add(i + 1);
        }
    }

    @Override
    public boolean possible(int n, int y, int x, Cell[][] grid){

        int box_x;
        int box_y;

        for(int horizontal = 0; horizontal < 9; horizontal++){
            if(grid[y][horizontal].getCellValue() == n) return false;
        }

        for(int vertical = 0; vertical < 9; vertical++){
            if(grid[vertical][x].getCellValue() == n) return false;
        }

        box_x = (int) (x / 3) * 3;
        box_y = (int) (y / 3) * 3;

        for(int i = box_x; i < box_x + 3; i++){
            for(int j = box_y; j < box_y + 3; j++){
                if(grid[j][i].getCellValue() == n) return false;
            }
        }
        return true;
    }

    @Override
    public void generateFilledGrid(Cell[][] grid){

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].setCellValue(0);
            }
        }

        fillGrid(grid);

        currentFilledGrid = new int[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                currentFilledGrid[i][j] = grid[i][j].getCellValue();
            }
        }

        removeValues(grid);
        kill = false;
    }

    @Override
    public Cell[][] fillGrid(Cell[][] grid){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++) {
                if(grid[y][x].getCellValue() == 0){
                    Collections.shuffle(array1to9);
                    for(int n: array1to9){
                        if(possible(n, y, x, grid)) {
                            grid[y][x].setCellValue(n);
                            grid[y][x].setLock(true);
                            fillGrid(grid);
                            if(!kill){
                                grid[y][x].setCellValue(0);
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

    @Override
    public void removeValues(Cell[][] grid){
        int[][] gridCopy = new int[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                gridCopy[i][j] = grid[i][j].getCellValue();
            }
        }

        int count = 65;
        solutions = 0;
        int rememberLastValue = 0;

        while(count > 0){

            int row = (int) (Math.random()*9);
            int column = (int) (Math.random()*9);

            if(grid[row][column].getCellValue() != 0){
                count--;
                rememberLastValue = grid[row][column].getCellValue();
                grid[row][column].setCellValue(0);
                grid[row][column].setLock(false);
                getNrOfSolutions(grid);

                if(solutions > 1 && count > 15){
                    count = 65;

                    for(int i = 0; i < 9; i++){
                        for(int j = 0; j < 9; j++){
                            grid[i][j].setCellValue(gridCopy[i][j]);
                            grid[i][j].setLock(true);
                        }
                    }

                }
                else if(solutions > 1){
                    grid[row][column].setCellValue(rememberLastValue);
                    grid[row][column].setLock(true);
                    break;
                }
            }

        }
    }

    @Override
    public int getNrOfSolutions(Cell[][] grid){
        solutions = 0;
        nrOfSolutions(grid);
        return solutions;
    }

    @Override
    protected Cell[][] nrOfSolutions(Cell[][] grid){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++) {
                if(grid[y][x].getCellValue() == 0){
                    for(int n = 1; n < 10; n++){
                        if(possible(n, y, x, grid)) {
                            grid[y][x].setCellValue(n);
                            nrOfSolutions(grid);
                            grid[y][x].setCellValue(0);
                        }
                    }
                    return grid;
                }
            }
        }
        solutions++;
        return grid;
    }

}