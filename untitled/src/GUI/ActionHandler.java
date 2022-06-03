package GUI;

import ADT.SudokuCell;
import Controll.Controll;
import Solver.Solver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
    JButton buttonExit;
    JButton buttonNewGame;
    JButton buttonStr8ts;
    JButton buttonSolve;
    SudokuCell[][] sudokuCell;
    Controll controll;

    public ActionHandler(JButton buttonExit, JButton buttonNewGame, JButton buttonStr8ts, JButton buttonSolve, SudokuCell[][] sudokuCell, Controll controll) {
        this.buttonExit = buttonExit;
        this.buttonNewGame = buttonNewGame;
        this.buttonSolve = buttonSolve;
        this.buttonStr8ts = buttonStr8ts;
        this.sudokuCell = sudokuCell;
        this.controll = controll;
    }
        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == buttonExit) {
                System.exit(0);
            } else if (e.getSource() == buttonNewGame) {
                //Code hier für ein neues Game --> Machen wir verschiedene Schwierigkeiten?
                controll.callSudokuGenerator();

            } else if (e.getSource() == buttonSolve) {
                //SudokuCell value vergleichen mit Solver
                controll.callSudokuSolver(sudokuCell);
            } else if (e.getSource() == buttonStr8ts) {
                //Code hier
            }
        }
}
