package Control;
import Sudoku.SudokuCell;
import GUI.GUI;
import Sudoku.SudokuGenerator;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Control {
    public boolean gewonnen = false;
    private int cluesUsed = 0;
    private final GUI gui;
    private final SudokuGenerator sudokuGenerator;
    public Control(){
        sudokuGenerator = new SudokuGenerator();
        gui = new GUI(this);
    }

    public static void main(String[] args){
        new Control();
    }

    public void callgetClue(SudokuCell[][] grid){
        boolean clueFound = false;
        int[][] checkGrid = sudokuGenerator.getCurrentFilledGrid();
        ArrayList<int[]> notFilledCells = new ArrayList<>();
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
            JOptionPane.showMessageDialog(null, "There are no clues available anymore");
        }
        else{
            gui.setClueCount(++cluesUsed);
            int random = (int) (Math.random() * notFilledCells.size());
            int y = notFilledCells.get(random)[0];
            int x = notFilledCells.get(random)[1];
            grid[y][x].setValueLayout();
            grid[y][x].setValueandDraw(checkGrid[y][x]);
            grid[y][x].markWithColor(new Color(255, 246, 179));
        }
    }
    public void callSudokuGenerator(SudokuCell[][] grid, String difficulty){
        cluesUsed = 0;
        sudokuGenerator.generateFilledGrid(grid, difficulty);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].markDefault();
                grid[i][j].drawValueOnGUI();
                for(int k = 0; k < 9; k++){
                    grid[i][j].getNotes()[k].setText("");
                }
            }
        }
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
                inputgrid[i][j].setTextColorDefault();
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
            gewonnen = false;
            JOptionPane.showMessageDialog(null, "Not Filled: " + notfilled + "\nWrong: " + wrong + "\nRight: " + right);
        }
        else {
            gewonnen = true;
            JFrame gewonnenFrame = new JFrame("Gewonnen");
            URL url;
            try{
                url = this.getClass().getResource("/rsc/DancingMonkey.gif");
                assert url != null;
                ImageIcon icon =  new ImageIcon(url);
                JLabel label = new JLabel(icon);

                label.setBounds(0, 0, 300, 216);
                gewonnenFrame.getContentPane().add(label);
                gewonnenFrame.pack();
                gewonnenFrame.setBounds(400, 400, 360, 276);
                gewonnenFrame.setLocationRelativeTo(null);
                gewonnenFrame.setResizable(false);

                gewonnenFrame.getContentPane().setBackground(Color.WHITE);
                gewonnenFrame.setVisible(true);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
