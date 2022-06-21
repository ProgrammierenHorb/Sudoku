package Actions;

import Sudoku.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ArrowLEFTAction extends AbstractAction {

    SudokuPanel sudokuPanel;

    public ArrowLEFTAction(SudokuPanel sudokuPanel){
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sudokuPanel.changeCurrentSelectedCell("LEFT");
    }

}
