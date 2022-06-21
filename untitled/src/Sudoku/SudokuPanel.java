package Sudoku;

import Controll.Controll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SudokuPanel extends JPanel {
    Controll theControll;
    JPanel gridPanel;
    JPanel buttonPanel;
    JButton buttonExit;
    JButton buttonClue;
    JButton buttonNewGame;
    JButton buttonCheck;
    SudokuCell[][] grid;
    SudokuCell currentSelectedCell;
    boolean shiftPressed = false;

    public SudokuPanel(Controll theControll) {
        setFocusable(true);
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
        initWriteInCells(grid);

        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Wirklich beenden?", "Programm beenden?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
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
                getTopLevelAncestor().requestFocus();
            }
        });
        createButton(buttonClue, 10, 275);

        buttonNewGame = new JButton("New Game");
        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theControll.callSudokuGenerator(grid);
                getTopLevelAncestor().requestFocus();
            }
        });
        createButton(buttonNewGame, 10, 25);

        buttonCheck = new JButton("Check");
        buttonCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theControll.callSudokuInputCheck(grid);
                getTopLevelAncestor().requestFocus();
            }
        });
        createButton(buttonCheck, 10, 150);

        theControll.callSudokuGenerator(grid);

        add(gridPanel);
        add(buttonPanel);


        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    shiftPressed = true;
                }
                if (!currentSelectedCell.isLocked()) {
                    if (!shiftPressed) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_1:
                                System.out.println("1");
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(1);
                                break;
                            case KeyEvent.VK_2:
                                System.out.println("2");
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(2);
                                break;
                            case KeyEvent.VK_3:
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(3);
                                break;
                            case KeyEvent.VK_4:
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(4);
                                break;
                            case KeyEvent.VK_5:
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(5);
                                break;
                            case KeyEvent.VK_6:
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(6);
                                break;
                            case KeyEvent.VK_7:
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(7);
                                break;
                            case KeyEvent.VK_8:
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(8);
                                break;
                            case KeyEvent.VK_9:
                                currentSelectedCell.setValueLayout();
                                currentSelectedCell.setValueandDraw(9);
                                break;
                            case KeyEvent.VK_BACK_SPACE:
                            case KeyEvent.VK_DELETE:
                                currentSelectedCell.setValueandDraw(0);
                                e.consume(); //verhindert dass Windows einen Fehlersound bei Eingaben wie BackSpace abspieltbreak;
                                break;
                        }
                    } else {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_1:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[0].setText("1");
                                break;
                            case KeyEvent.VK_2:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[1].setText("2");
                                break;
                            case KeyEvent.VK_3:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[2].setText("3");
                                break;
                            case KeyEvent.VK_4:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[3].setText("4");
                                break;
                            case KeyEvent.VK_5:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[4].setText("5");
                                break;
                            case KeyEvent.VK_6:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[5].setText("6");
                                break;
                            case KeyEvent.VK_7:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[6].setText("7");
                                break;
                            case KeyEvent.VK_8:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[7].setText("8");
                                break;
                            case KeyEvent.VK_9:
                                currentSelectedCell.setNotesLayout();
                                currentSelectedCell.notes[8].setText("9");
                                break;
                            case KeyEvent.VK_BACK_SPACE:
                            case KeyEvent.VK_DELETE:
                                currentSelectedCell.setValueandDraw(0);
                                e.consume(); //verhindert dass Windows einen Fehlersound bei Eingaben wie BackSpace abspieltbreak;
                                break;
                        }
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    shiftPressed = false;
                }
            }
        });
    }

    private void initSudokuField() {

        grid = new SudokuCell[9][9];

        //Füllt das Sudokufeld Array mit SudokuCell Objekten
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new SudokuCell(new int[]{i, j});
                gridPanel.add(grid[i][j]);
            }
        }
    }

    private void markCellsWhenSelected() {
        Color colormarkselected = new Color(215, 255, 255);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int finalJ = j;
                int finalI = i;
                grid[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (i == finalI) { //Markiert Reihe
                                    grid[i][j].markWithColor(colormarkselected);
                                } else if (j == finalJ) { //Markiert Spalte
                                    grid[i][j].markWithColor(colormarkselected);
                                } else if ((i / 3) == (finalI / 3) && (j / 3) == (finalJ / 3)) { //Markiert Box
                                    grid[i][j].markWithColor(colormarkselected);
                                } else {
                                    grid[i][j].markDefault();
                                }
                            }
                        }
                        grid[finalI][finalJ].markWithColor(new Color(140, 236, 239)); //Markiert angeklicktes Feld in einer etwas anderen Farbe
                    }
                });

            }
        }
    }

    private void initWriteInCells(SudokuCell[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int finalI = i;
                int finalJ = j;
                grid[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        currentSelectedCell = grid[finalI][finalJ];
                        System.out.print(finalI);
                        System.out.println(finalJ);
                    }
                });
            }
        }
    }

    public void createButton(JButton button, int x, int y) {
        setFocusable(false);
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

