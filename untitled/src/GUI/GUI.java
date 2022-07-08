package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Controll.Controll;
import Killer.KillerPanel;
import Sudoku.SudokuPanel;

public class GUI {
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu modeSelection;
    private JMenuItem difficulty_easy;
    private JMenuItem difficulty_default;
    private final int SCREEN_WIDTH = 900;
    private final int SCREEN_HEIGHT = 620;

    public GUI(Controll theControll){

        frame = new JFrame("Sudoku");
        frame.setResizable(false);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);

        SudokuPanel sudokuPanel = new SudokuPanel(theControll);
        frame.setContentPane(sudokuPanel);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBounds(0,0, SCREEN_WIDTH, 30);

        frame.add(menuBar);

        modeSelection = new JMenu("Change difficulty");
        menuBar.add(modeSelection);

        difficulty_easy = new JMenuItem("Easy");
        modeSelection.add(difficulty_easy);
        difficulty_default = new JMenuItem("Default");
        modeSelection.add(difficulty_default);

        difficulty_easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudokuPanel.setDifficulty("easy");
            }
        });

        difficulty_default.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudokuPanel.setDifficulty("default");
            }
        });
        frame.setVisible(true);
    }

}
