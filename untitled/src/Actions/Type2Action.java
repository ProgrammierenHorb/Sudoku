package Actions;

import Controll.Cell;
import Sudoku.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Type2Action extends AbstractAction {

    SudokuPanel sudokuPanel;
    boolean shiftpressed;

    public Type2Action(SudokuPanel sudokuPanel, boolean shiftpressed){
        this.shiftpressed = shiftpressed;
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(shiftpressed){
            sudokuPanel.triggerWriteValue(2);
        }
        else{
            sudokuPanel.triggerWriteNote(2);
        }
    }
}
