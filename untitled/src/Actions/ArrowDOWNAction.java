package Actions;

import Sudoku.SudokuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ArrowDOWNAction extends AbstractAction {

    SudokuPanel sudokuPanel;

    public ArrowDOWNAction(SudokuPanel sudokuPanel){
        this.sudokuPanel = sudokuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sudokuPanel.changeCurrentSelectedCell("DOWN");
    }

}
