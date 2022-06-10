package GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import ADT.SudokuCell;
import Controll.Controll;

public class GUI {
    private DrawSudokuField draw;
    private JFrame frame;
    private JPanel sudokuPanel;
    private JPanel killerPanel;
    private JPanel str8tsPanel;
    private SudokuCell[][] grid;
    private JPanel gridPanel, buttonPanel, drawPanel;
    private JButton buttonClue, buttonNewGame, buttonExit, buttonCheck;

    private JMenuBar menuBar;
    private JMenu modeSelection;
    private JMenuItem sudoku;
    private JMenuItem killer;
    private JMenuItem str8ts;

    private Controll theControll;

    public GUI(Controll theControll){
        this.theControll = theControll;

        grid = new SudokuCell[9][9];
        frame = new JFrame("Sudoku");
        frame.setResizable(false);
        frame.setSize(900, 620);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);

        sudokuPanel = new JPanel();
        sudokuPanel.setBounds(0, 75, 900, 600);
        sudokuPanel.setLayout(null);
        sudokuPanel.setBackground(Color.white);

        killerPanel = new JPanel();
        killerPanel.setBounds(0, 75, 900, 600);
        killerPanel.setLayout(null);
        killerPanel.setBackground(Color.white);

        str8tsPanel = new JPanel();
        str8tsPanel.setBounds(0, 75, 900, 600);
        str8tsPanel.setLayout(null);
        str8tsPanel.setBackground(Color.white);

        frame.setContentPane(sudokuPanel);

        gridPanel = new JPanel();
        gridPanel.setBounds(25, 50, 500, 500);
        gridPanel.setBackground(Color.GRAY);
        gridPanel.setLayout(new GridLayout(9, 9));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        buttonPanel = new JPanel();
        buttonPanel.setBounds(575, 50, 300, 500);
        buttonPanel.setLayout(null);

        drawPanel = new JPanel();
        drawPanel.setBounds(25, 25, 500, 500);
        drawPanel.setBackground(Color.GRAY);
        drawPanel.setLayout(null);

        menuBar = new JMenuBar();
        menuBar.setBounds(10, 25, 275, 25);
        menuBar.setBackground(Color.WHITE);
        menuBar.setBounds(0,0, 900, 30);
        menuBar.setBackground(Color.GRAY);

        frame.add(menuBar);

        modeSelection = new JMenu("Modus auswählen");
        menuBar.add(modeSelection);

        sudoku = new JMenuItem("Sudoku");
        modeSelection.add(sudoku);
        killer = new JMenuItem("Killer");
        modeSelection.add(killer);
        str8ts = new JMenuItem("Str8ts");
        modeSelection.add(str8ts);

        sudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("sudoku");
                frame.setContentPane(sudokuPanel);
                frame.add(menuBar);
                frame.validate();
            }
        });

        killer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("killer");
                frame.setContentPane(killerPanel);
                frame.add(menuBar);
                frame.validate();
            }
        });

        str8ts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("str8ts");
                frame.setContentPane(str8tsPanel);
                frame.add(menuBar);
                frame.validate();
            }
        });


        buttonExit = new JButton("Exit");
        createButton(buttonExit, 10, 400);

        buttonClue = new JButton("Get Clue");
        createButton(buttonClue, 10, 275);

        buttonNewGame = new JButton("New Game");
        createButton(buttonNewGame, 10, 25);

        buttonCheck = new JButton("Check");
        createButton(buttonCheck, 10, 150);

        //Füllt das Sudokufeld Array mit SudokuCell Objekten
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j] = new SudokuCell(new int[] {i, j});
                gridPanel.add(grid[i][j]);
            }
        }

        //Markiert relvevante Felder bei klick auf ein Feld
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

        draw = new DrawSudokuField();
        draw.setBounds(0, 0, 500, 500);
        draw.setVisible(true);

        sudokuPanel.add(gridPanel);
        sudokuPanel.add(buttonPanel);

        frame.setVisible(true);

        theControll.callSudokuGenerator(grid);
    }


    public void createButton(JButton button, int x, int y){
        button.setBounds(x, y, 275, 75);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setBorder(null);
        button.setFocusPainted(false);
        button.addActionListener(new ActionHandler(buttonExit, buttonNewGame, buttonClue, buttonCheck, grid, theControll));
        button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        JButton finalButton = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                finalButton.setBorder(BorderFactory.createLineBorder(Color.orange, 3));
                finalButton.setForeground(Color.BLACK);
            }
            public void mouseExited(MouseEvent evt) {
                finalButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                finalButton.setForeground(Color.BLACK);
            }
        });
        buttonPanel.add(button);
    }
}
