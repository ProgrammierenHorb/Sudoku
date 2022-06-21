package Actions;

import Sudoku.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ArrowUPAction extends AbstractAction {

    SudokuPanel sudokuPanel;

    public ArrowUPAction(SudokuPanel sudokuPanel){
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sudokuPanel.changeCurrentSelectedCell("UP");

    }

}
