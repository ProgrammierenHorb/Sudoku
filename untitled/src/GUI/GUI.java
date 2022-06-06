package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ADT.SudokuCell;
import Controll.Controll;

public class GUI {
    private DrawSudokuField draw;
    public JFrame frame;
    public SudokuCell[][] grid;
    public JPanel gridPanel, buttonPanel, drawPanel;
    private JButton buttonSolve, buttonNewGame, buttonExit, buttonStr8ts;

    private JComboBox modeSelection;
    private Controll theControll;

    public GUI(Controll theControll){
        this.theControll = theControll;

        grid = new SudokuCell[9][9];
        frame = new JFrame("Sudoku");
        frame.setResizable(false);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);

        gridPanel = new JPanel();
        gridPanel.setBounds(25, 25, 500, 500);
        gridPanel.setBackground(Color.GRAY);
        gridPanel.setLayout(new GridLayout(9, 9));

        buttonPanel = new JPanel();
        buttonPanel.setBounds(575, 25, 300, 500);
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(null);

        drawPanel = new JPanel();
        drawPanel.setBounds(25, 25, 500, 500);
        drawPanel.setBackground(Color.GRAY);
        drawPanel.setLayout(null);

        modeSelection = new JComboBox(new String[]{"Sudoku", "Killer Sudoku", "Str8ts"});
        modeSelection.setBounds(10, 25, 275, 25);
        modeSelection.setBackground(Color.WHITE);
        buttonPanel.add(modeSelection);

        buttonExit = new JButton("Exit");
        createButton(buttonExit, 10, 400);

        buttonSolve = new JButton("Solve");
        createButton(buttonSolve, 10, 275);

        buttonNewGame = new JButton("New Game");
        createButton(buttonNewGame, 10, 150);

        //buttonStr8ts = new JButton("Stra8ts");
        //createButton(buttonStr8ts, 10, 25);

        //Füllt das Sudokufeld Array mit SudokuCell Objekten
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j] = new SudokuCell();
                createTextBox(grid[i][j]);
            }
        }

        draw = new DrawSudokuField();
        draw.setBounds(0, 0, 500, 500);
        draw.setVisible(true);
        //GUI.Var.drawPanel.add(draw);

        frame.add(gridPanel);
        frame.add(buttonPanel);
        //frame.add(canvas);
        //GUI.Var.frame.add(GUI.Var.drawPanel);
        //Var.frame.add(draw);
        frame.setVisible(true);
    }
    public void createTextBox(SudokuCell sudokuCell){
        //textField.setBorder(null);
        sudokuCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sudokuCell.setBackground(Color.lightGray);

            }
        });
        gridPanel.add(sudokuCell);
    }

    public void createButton(JButton button, int x, int y){
        button.setBounds(x, y, 275, 75);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.setBorder(null);
        button.setFocusPainted(false);
        button.addActionListener(new ActionHandler(buttonExit, buttonNewGame, buttonStr8ts, buttonSolve, grid, theControll));
        JButton finalButton = button;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                finalButton.setBackground(Color.CYAN);
                finalButton.setForeground(Color.BLACK);
            }
            public void mouseExited(MouseEvent evt) {
                finalButton.setBackground(Color.GRAY);
                finalButton.setForeground(Color.BLACK);
            }
        });
        buttonPanel.add(button);
    }
}
