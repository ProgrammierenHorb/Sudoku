package Controll;
import Killer.KillerCell;
import Killer.KillerGenerator;
import Sudoku.SudokuCell;
import GUI.GUI;
import Sudoku.SudokuGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Controll {
    private GUI theGUI;
    public Controll(){

        theGUI = new GUI(this);

    }

    public static void main(String[] args){

        Controll theControll = new Controll();

    }

    SudokuGenerator sudokuGenerator = new SudokuGenerator();
    KillerGenerator killerGenerator = new KillerGenerator();

    public void callgetClue(SudokuCell[][] grid){
        boolean clueFound = false;
        int[][] checkGrid = sudokuGenerator.getCurrentFilledGrid();
        List<int[]> notFilledCells = new ArrayList();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].updateValue();
                if(grid[i][j].getCellValue() == 0){
                    notFilledCells.add(new int[] {i, j});
                    clueFound = true;
                }
            }
        }
        if(!clueFound){
            JOptionPane.showMessageDialog(null, "Keine weiteren Tipps möglich");
        }
        else{
            int random = (int) (Math.random() * notFilledCells.size());
            int y = notFilledCells.get(random)[0];
            int x = notFilledCells.get(random)[1];
            grid[y][x].setValueandDraw(checkGrid[y][x]);
            grid[y][x].markWithColor(new Color(255, 246, 179));
        }
    }
    public void callSudokuGenerator(SudokuCell[][] grid){
        sudokuGenerator.generateFilledGrid(grid);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].markDefault();
                grid[i][j].drawValueOnGUI();
            }
        }
    }

    public void callKillerGenerator(KillerCell[][] grid){
        /*killerGenerator.generateFilledGrid(grid);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                //grid[i][j].markDefault();
                grid[i][j].drawValueOnGUI();
            }
        }*/
    }

    public void callSudokuInputCheck(SudokuCell[][] inputgrid){

        int[][] checkGrid = sudokuGenerator.getCurrentFilledGrid();

        boolean won = true;

        int wrong = 0;
        int right = 0;
        int notfilled = 0;

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                inputgrid[i][j].updateValue();
                if(!inputgrid[i][j].isLocked()){
                    if(inputgrid[i][j].getCellValue() == 0){
                        inputgrid[i][j].markDefault();
                        won = false;
                        notfilled++;
                    } else if (inputgrid[i][j].getCellValue() != checkGrid[i][j]) {
                        inputgrid[i][j].markWithColor(new Color(255, 168, 168));
                        won = false;
                        wrong++;
                    } else {
                        inputgrid[i][j].markWithColor(new Color(179, 255, 202));
                        right++;
                    }
                }
                else{
                    inputgrid[i][j].markDefault();
                }
            }
        }
        if(!won){
            JOptionPane.showMessageDialog(null, "Not Filled: " + notfilled + "\nWrong: " + wrong + "\nRight: " + right);
        }
        else {
            JOptionPane.showMessageDialog(null, "Gewinde Gewinde Gewinde");
        }
    }
}
