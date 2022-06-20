package Killer;

import Sudoku.SudokuCell;
import Sudoku.SudokuGenerator;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class KillerGenerator{
/*
    private KillerCell[][] currentFilledGrid;

    private Map<Integer, Integer> sums;
    int solutions;

    public KillerGenerator() {
        super();
    }

    public KillerCell[][] generateFilledGrid(KillerCell[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setValue(0);
                grid[i][j].setSumKey(-1);
            }
        }

        fillGrid(grid);
        sums = createAndGetSumFields(grid);

        removeDigits(grid, sums);

        currentFilledGrid = new KillerCell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                currentFilledGrid[i][j] = new KillerCell();
                currentFilledGrid[i][j].setValue(grid[i][j].getIntValue());
                currentFilledGrid[i][j].setSumKey(grid[i][j].getSumKey());
            }
        }

        Random rand = new Random();
        for (int k : sums.keySet()) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color color = new Color(r, g, b);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (grid[i][j].getSumKey() == k) {
                        grid[i][j].markwithColor(color);
                    }
                }
            }
        }
        kill = false;
        return grid;
    }

    public Map<Integer, Integer> createAndGetSumFields(KillerCell[][] grid) {

        Map<Integer, Integer> sums = new HashMap<>();
        int sumKey = 0;
        int sumOfCells = 0;
        int notAllowedNeighbour;
        final int MAX_SIZE = 6;
        int currentSize;
        int[] currentCoordinates = new int[2];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (grid[i][j].getSumKey() == -1) {

                    currentSize = 1;
                    grid[i][j].setSumKey(sumKey);
                    sumOfCells += grid[i][j].getIntValue();
                    currentCoordinates[0] = i;
                    currentCoordinates[1] = j;
                    sums.put(sumKey, 0);
                    int sizeOfFields = (int) (Math.random() * 5);

                    while (sizeOfFields != 0 && MAX_SIZE != currentSize) {

                        sizeOfFields = (int) (Math.random() * 5);

                        List<int[]> neighbours = getNeighbours(currentCoordinates[0], currentCoordinates[1], grid);
                        Collections.shuffle(neighbours);

                        notAllowedNeighbour = 0;

                        for (int[] neighbour : neighbours) {
                            if (grid[neighbour[0]][neighbour[1]].getSumKey() == -1) {
                                grid[neighbour[0]][neighbour[1]].setSumKey(sumKey);
                                sumOfCells += grid[neighbour[0]][neighbour[1]].getIntValue();
                                currentCoordinates[0] = neighbour[0];
                                currentCoordinates[1] = neighbour[1];
                                currentSize++;
                                break;
                            } else {
                                notAllowedNeighbour++;
                            }
                        }

                        if (notAllowedNeighbour >= neighbours.size()) {
                            break;
                        }
                    }
                    sums.put(sumKey, sumOfCells);
                    sumKey++;
                    sumOfCells = 0;
                }
            }
        }
        return sums;
    }

    public List<int[]> getNeighbours(int posY, int posX, KillerCell[][] grid) {

        List<int[]> neighbours = new ArrayList<>();

        if (posY > 0) {
            neighbours.add(new int[]{posY - 1, posX});
        }
        if (posY < 8) {
            neighbours.add(new int[]{posY + 1, posX});
        }
        if (posX > 0) {
            neighbours.add(new int[]{posY, posX - 1});
        }
        if (posX < 8) {
            neighbours.add(new int[]{posY, posX + 1});
        }

        return neighbours;
    }

    public boolean possible(int n, int y, int x, KillerCell[][] grid, Map<Integer, Integer> sums){

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

        int currentSumOfField = 0;
        int sumKey = grid[y][x].getSumKey();
        int maxSumOfField = sums.get(sumKey);

        for(int k = 0; k < 9; k++){
            for(int l = 0; l < 9; l++){
                if(grid[k][l].getSumKey() == sumKey){
                    currentSumOfField += grid[k][l].getIntValue();
                }
            }
        }

        if(currentSumOfField + n > maxSumOfField) return false;

        return true;
    }

    public int getNrofSolutions(KillerCell[][] grid, Map<Integer, Integer> sums){
        solutions = 0;
        NrOfSolutions(grid, sums);
        return solutions;
    }

    private SudokuCell[][] NrOfSolutions(KillerCell[][] grid, Map<Integer, Integer> sums){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++) {
                if(grid[y][x].getIntValue() == 0){
                    for(int n = 1; n < 10; n++){
                        if(possible(n, y, x, grid, sums)) {
                            grid[y][x].setValue(n);
                            NrOfSolutions(grid, sums);
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

    public void removeDigits(KillerCell[][] grid, Map<Integer, Integer> sums){
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
                getNrofSolutions(grid, sums);

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
    }*/
}
