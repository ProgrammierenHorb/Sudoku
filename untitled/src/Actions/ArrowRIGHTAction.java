package Actions;

import Sudoku.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ArrowRIGHTAction extends AbstractAction {

    SudokuPanel sudokuPanel;

    public ArrowRIGHTAction(SudokuPanel sudokuPanel){
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sudokuPanel.changeCurrentSelectedCell("RIGHT");
    }

}
