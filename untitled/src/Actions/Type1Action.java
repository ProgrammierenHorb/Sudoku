package Actions;

import Controll.Cell;
import Sudoku.SudokuCell;
import Sudoku.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Type1Action extends AbstractAction {

    SudokuPanel sudokuPanel;
    boolean shiftpressed;

    public Type1Action(SudokuPanel sudokuPanel, boolean shiftpressed){
        this.shiftpressed = shiftpressed;
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(shiftpressed){
            sudokuPanel.triggerWriteValue(1);
        }
        else{
            sudokuPanel.triggerWriteNote(1);
        }

        
    }
    

}
