package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Controll.Controll;
import Killer.KillerPanel;
import Sudoku.SudokuPanel;

public class GUI {

    private JFrame frame;
    private JPanel killerPanel;
    private JPanel str8tsPanel;

    private JMenuBar menuBar;
    private JMenu modeSelection;
    private JMenuItem sudoku;
    private JMenuItem killer;
    private JMenuItem str8ts;

    private Controll theControll;

    public GUI(Controll theControll){
        this.theControll = theControll;

        frame = new JFrame("Sudoku");
        frame.setResizable(false);
        frame.setSize(900, 620);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);

        JPanel sudokuPanel = new SudokuPanel(theControll);
        frame.setContentPane(sudokuPanel);

        JPanel killerPanel = new KillerPanel(theControll);
        //JPanel str8tsPanel = new Str8tsPanel(theControll);

        menuBar = new JMenuBar();
        menuBar.setBounds(10, 25, 275, 25);
        menuBar.setBackground(Color.WHITE);
        menuBar.setBounds(0,0, 900, 30);

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

        frame.setVisible(true);


    }

}
