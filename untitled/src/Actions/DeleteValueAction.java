package Actions;

import Sudoku.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteValueAction extends AbstractAction {

    SudokuPanel sudokuPanel;

    public DeleteValueAction(SudokuPanel sudokuPanel){
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sudokuPanel.removeNotes();
        sudokuPanel.triggerWriteValue(0);


    }

}
