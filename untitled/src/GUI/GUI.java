package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import Control.Control;
import Sudoku.SudokuPanel;

public class GUI {
    private final SudokuPanel sudokuPanel;
    private final Control theControll;

    public GUI(Control theControll){
        this.theControll = theControll;
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
        JMenu helpSelection = new JMenu("Change help Mode");
        menuBar.add(helpSelection);

        JRadioButtonMenuItem difficulty_easy = new JRadioButtonMenuItem("Easy");
        modeSelection.add(difficulty_easy);
        JRadioButtonMenuItem difficulty_default = new JRadioButtonMenuItem("Default");
        modeSelection.add(difficulty_default);

        difficulty_easy.addActionListener(e -> {
            sudokuPanel.setDifficulty("easy");
            difficulty_default.setSelected(false);
        });

        difficulty_default.addActionListener(e -> {
            sudokuPanel.setDifficulty("default");
            difficulty_easy.setSelected(false);
        });

        JRadioButtonMenuItem help_ON = new JRadioButtonMenuItem("ON");
        helpSelection.add(help_ON);
        JRadioButtonMenuItem help_OFF = new JRadioButtonMenuItem("OFF");
        helpSelection.add(help_OFF);

        help_ON.addActionListener(e -> {
            sudokuPanel.sethelpActivated(true);
            help_OFF.setSelected(false);
        });

        help_OFF.addActionListener(e -> {
            sudokuPanel.sethelpActivated(false);
            help_ON.setSelected(false);
        });

        difficulty_default.setSelected(true);
        help_ON.setSelected(true);
        frame.setVisible(true);
    }

    public void setClueCount(int clues){
        sudokuPanel.setClueCount(clues);
    }
}
