package Sudoku;

import Actions.*;
import Controll.Controll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Type;

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

        InputMap im = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke('1'), "write1");
        am.put("write1", new Type1Action(this, false));

        im.put(KeyStroke.getKeyStroke('1', InputEvent.SHIFT_DOWN_MASK), "write1shift");
        am.put("write1shift", new Type1Action(this, true));

        im.put(KeyStroke.getKeyStroke('2'), "write2");
        am.put("write2", new Type2Action(this, false));

        im.put(KeyStroke.getKeyStroke('2', InputEvent.SHIFT_DOWN_MASK), "write2shift");
        am.put("write2shift", new Type2Action(this, true));

        im.put(KeyStroke.getKeyStroke('3'), "write3");
        am.put("write3", new Type3Action(this, false));

        im.put(KeyStroke.getKeyStroke('3', InputEvent.SHIFT_DOWN_MASK), "write3shift");
        am.put("write3shift", new Type3Action(this, true));

        im.put(KeyStroke.getKeyStroke('4'), "write4");
        am.put("write4", new Type4Action(this, false));

        im.put(KeyStroke.getKeyStroke('4', InputEvent.SHIFT_DOWN_MASK), "write4shift");
        am.put("write4shift", new Type4Action(this, true));

        im.put(KeyStroke.getKeyStroke('5'), "write5");
        am.put("write5", new Type5Action(this, false));

        im.put(KeyStroke.getKeyStroke('5', InputEvent.SHIFT_DOWN_MASK), "write5shift");
        am.put("write5shift", new Type5Action(this, true));

        im.put(KeyStroke.getKeyStroke('6'), "write6");
        am.put("write6", new Type6Action(this, false));

        im.put(KeyStroke.getKeyStroke('6', InputEvent.SHIFT_DOWN_MASK), "write6shift");
        am.put("write6shift", new Type6Action(this, true));

        im.put(KeyStroke.getKeyStroke('7'), "write7");
        am.put("write7", new Type7Action(this, false));

        im.put(KeyStroke.getKeyStroke('7', InputEvent.SHIFT_DOWN_MASK), "write7shift");
        am.put("write7shift", new Type7Action(this, true));

        im.put(KeyStroke.getKeyStroke('8'), "write8");
        am.put("write8", new Type8Action(this, false));

        im.put(KeyStroke.getKeyStroke('8', InputEvent.SHIFT_DOWN_MASK), "write8shift");
        am.put("write8shift", new Type8Action(this, true));

        im.put(KeyStroke.getKeyStroke('9'), "write9");
        am.put("write9", new Type9Action(this, false));

        im.put(KeyStroke.getKeyStroke('9', InputEvent.SHIFT_DOWN_MASK), "write9shift");
        am.put("write9shift", new Type9Action(this, true));

        im.put(KeyStroke.getKeyStroke("BACK_SPACE"), "delete");
        am.put("delete", new DeleteValueAction(this));

    }

    public void triggerWriteValue(int value){
        currentSelectedCell.setValueLayout();
        currentSelectedCell.setValueandDraw(value);
    }

    public void triggerWriteNote(int value){
        currentSelectedCell.setNotesLayout();
        if(currentSelectedCell.notes[value-1].getText() == ""){
            currentSelectedCell.notes[value-1].setText(String.valueOf(value));
        }
        else{
            currentSelectedCell.notes[value-1].setText("");
        }
    }

    public void removeNotes(){
        for(int i = 0; i<9; i++){
            currentSelectedCell.notes[i].setText("");
        }

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

