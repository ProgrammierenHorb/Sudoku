package GUI;

import ADT.SudokuCell;
import Controll.Controll;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
    JButton buttonExit;
    JButton buttonNewGame;
    JButton buttonClue;
    JButton buttonCheck;
    SudokuCell[][] grid;
    Controll controll;

    public ActionHandler(JButton buttonExit, JButton buttonNewGame, JButton buttonClue, JButton buttonCheck, SudokuCell[][] grid, Controll controll) {
        this.buttonExit = buttonExit;
        this.buttonNewGame = buttonNewGame;
        this.buttonClue = buttonClue;
        this.buttonCheck = buttonCheck;
        this.grid = grid;
        this.controll = controll;
    }
        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == buttonExit) {
                int reply = JOptionPane.showConfirmDialog(null, "Wirklich beenden?", "Programm beenden?", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION){
                    System.exit(0);
                }

            } else if (e.getSource() == buttonNewGame) {
                //Code hier für ein neues Game --> Machen wir verschiedene Schwierigkeiten?
                controll.callSudokuGenerator(grid);
            } else if (e.getSource() == buttonClue) {
                //SudokuCell value vergleichen mit Solver
                controll.callgetClue(grid);
            } else if (e.getSource() == buttonCheck){
                controll.callSudokuInputCheck(grid);
            }
        }
}
