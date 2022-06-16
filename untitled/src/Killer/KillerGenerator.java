package Killer;

import Sudoku.SudokuCell;
import Sudoku.SudokuGenerator;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class KillerGenerator extends SudokuGenerator {

    private KillerCell[][] currentFilledGrid;

    public KillerGenerator(){
        super();
    }

    public KillerCell[][] generateFilledGrid(KillerCell[][] grid){

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

        //removeDigits(grid);
        Map<Integer, Integer> sums = createAndGetSumFields(grid);
        System.out.println(sums.keySet());
        Random rand = new Random();
        for(int k: sums.keySet()){
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color color = new Color(r,g,b);
            System.out.println("-------------------------");
            System.out.println("KeySet: " + k + " with total sum of " + sums.get(k)+ " contains: ");
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(grid[i][j].getSumKey() == k){
                        grid[i][j].markwithColor(color);
                        System.out.println("Y: " + i + " X: " + j);
                    }
                }
            }
        }

        kill = false;
        return grid;
    }

    public Map<Integer, Integer> createAndGetSumFields(KillerCell[][] grid){
        Map<Integer, Integer> sums = new HashMap<>();
        int sumKey = 0;
        int sumOfCells = 0;
        final int MAX_SIZE = 6;

        boolean extendField = true;
        int[] currentCoordinates = new int[2];
        System.out.println("check");

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){

                if (grid[i][j].getSumKey() == -1){

                    int currentSize = 1;
                    KillerCell currentField = grid[i][j];
                    currentField.setSumKey(sumKey);
                    sumOfCells += currentField.getIntValue();
                    currentCoordinates[0] = i;
                    currentCoordinates[1] = j;
                    sums.put(sumKey, 0);

                    while(true){
                        int sizeOfFields = (int) (Math.random()*5);
                        if(sizeOfFields == 0 || currentSize == MAX_SIZE){
                            break;
                        }

                        List<KillerCell> neighbours = getNeighbours(currentCoordinates[0], currentCoordinates[1], grid);
                        Collections.shuffle(neighbours);

                        int notAllowedNeighbour = 0;

                        for(KillerCell neighbour: neighbours){
                            if(neighbour.getSumKey() == -1){
                                currentSize++;
                                neighbour.setSumKey(sumKey);
                                sumOfCells += neighbour.getIntValue();
                                currentCoordinates = neighbour.getPosition();
                                break;
                            }
                            else{
                                notAllowedNeighbour++;
                            }
                        }

                        if(notAllowedNeighbour >= neighbours.size()){
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

    public List<KillerCell> getNeighbours(int posY, int posX, KillerCell[][] grid){

        List<KillerCell> neighbours = new ArrayList<>();


        if(posY > 0){
            neighbours.add(grid[posY-1][posX]);
        }
        if(posY < 8){
            neighbours.add(grid[posY+1][posX]);
        }
        if(posX > 0){
            neighbours.add(grid[posY][posX-1]);
        }
        if(posX < 8){
            neighbours.add(grid[posY][posX+1]);
        }

        return neighbours;
    }
}
