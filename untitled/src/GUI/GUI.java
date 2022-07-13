package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import Control.Control;
import Sudoku.SudokuPanel;

public class GUI {
    private final SudokuPanel sudokuPanel;

    public GUI(Control theControll){

        JFrame frame = new JFrame("Sudoku");
        frame.setResizable(false);
        try {
            URL url = this.getClass().getResource("/rsc/sudoku-logo.png");
            assert url != null;
            ImageIcon icon =  new ImageIcon(url);
            frame.setIconImage(icon.getImage());
        }catch(Exception e){
            e.printStackTrace();
        }
        int SCREEN_WIDTH = 900;
        int SCREEN_HEIGHT = 620;
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBackground(Color.white);

        sudokuPanel = new SudokuPanel(theControll);
        frame.setContentPane(sudokuPanel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        menuBar.setBounds(0,0, SCREEN_WIDTH, 30);

        frame.add(menuBar);

        JMenu modeSelection = new JMenu("Change difficulty");
        menuBar.add(modeSelection);

        JMenuItem difficulty_easy = new JMenuItem("Easy");
        modeSelection.add(difficulty_easy);
        JMenuItem difficulty_default = new JMenuItem("Default");
        modeSelection.add(difficulty_default);

        difficulty_easy.addActionListener(e -> sudokuPanel.setDifficulty("easy"));

        difficulty_default.addActionListener(e -> sudokuPanel.setDifficulty("default"));
        frame.setVisible(true);
    }

    public void setClueCount(int clues){
        sudokuPanel.setClueCount(clues);
    }
}
