package Sudoku;

import Controll.Controll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuPanel extends JPanel{
    Controll theControll;
    JPanel gridPanel;
    JPanel buttonPanel;
    JButton buttonExit;
    JButton buttonClue;
    JButton buttonNewGame;
    JButton buttonCheck;
    SudokuCell[][] grid;

    public SudokuPanel(Controll theControll){

        this.theControll = theControll;
        setBounds(0, 75, 900, 600);
        setLayout(null);
        setBackground(Color.white);

        gridPanel = new JPanel();
        gridPanel.setBounds(25, 50, 500, 500);
        gridPanel.setBackground(Color.GRAY);
        gridPanel.setLayout(new GridLayout(9, 9));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        buttonPanel = new JPanel();
        buttonPanel.setBounds(575, 50, 300, 500);
        buttonPanel.setLayout(null);

        initSudokuField();
        markCellsWhenSelected();

        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Wirklich beenden?", "Programm beenden?", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        createButton(buttonExit, 10, 400);

        buttonClue = new JButton("Get Clue");
        buttonClue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theControll.callgetClue(grid);
            }
        });
        createButton(buttonClue, 10, 275);

        buttonNewGame = new JButton("New Game");
        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theControll.callSudokuGenerator(grid);
            }
        });
        createButton(buttonNewGame, 10, 25);

        buttonCheck = new JButton("Check");
        buttonCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theControll.callSudokuInputCheck(grid);
            }
        });
        createButton(buttonCheck, 10, 150);

        theControll.callSudokuGenerator(grid);

        add(gridPanel);
        add(buttonPanel);



    }

    private void initSudokuField(){

        grid = new SudokuCell[9][9];

        //Füllt das Sudokufeld Array mit SudokuCell Objekten
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j] = new SudokuCell(new int[] {i, j});
                gridPanel.add(grid[i][j]);
            }
        }
    }

    private void markCellsWhenSelected(){
        Color colormarkselected = new Color(215, 255, 255);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                int finalJ = j;
                int finalI = i;
                grid[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        for(int i = 0; i < 9; i++){
                            for(int j = 0; j < 9; j++){
                                if(i == finalI){ //Markiert Reihe
                                    grid[i][j].markwithColor(colormarkselected);
                                }
                                else if(j == finalJ){ //Markiert Spalte
                                    grid[i][j].markwithColor(colormarkselected);
                                }
                                else if((i / 3) == (finalI/3) && (j / 3) == (finalJ/3)){ //Markiert Box
                                    grid[i][j].markwithColor(colormarkselected);
                                }
                                else{
                                    grid[i][j].markDefault();
                                }
                            }
                        }
                        grid[finalI][finalJ].markwithColor(new Color(140, 236, 239)); //Markiert angeklicktes Feld in einer etwas anderen Farbe
                    }
                });

            }
        }
    }

    public void createButton(JButton button, int x, int y){
        button.setBounds(x, y, 275, 75);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        JButton finalButton = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                finalButton.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
            }
            public void mouseExited(MouseEvent evt) {
                finalButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            }
        });
        buttonPanel.add(button);
    }
}

