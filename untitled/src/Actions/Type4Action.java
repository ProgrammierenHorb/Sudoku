package Actions;

import Sudoku.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Type4Action extends AbstractAction {

    SudokuPanel sudokuPanel;
    boolean shiftpressed;

    public Type4Action(SudokuPanel sudokuPanel, boolean shiftpressed) {
        this.shiftpressed = shiftpressed;
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (shiftpressed) {
            sudokuPanel.triggerWriteValue(4);
        } else {
            sudokuPanel.triggerWriteNote(4);
        }

    }
}
