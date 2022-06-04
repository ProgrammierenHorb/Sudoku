package ADT;

import javax.swing.*;
import java.awt.*;

public class SudokuCell extends JTextField {
    Font sudokuCell = new Font("Arial", Font.BOLD, 20);
    private int cellInt;
    private boolean isLocked;

    public SudokuCell(){
        setValue(0);
        setLock(false);
        init();
    }
    public SudokuCell(int valInt, boolean lock){
        setValue(valInt);
        setLock(lock);
        init();
    }

    private void init(){
        setHorizontalAlignment(JTextField.CENTER);
        setFont(sudokuCell);
    }

    public void setValue(String Value){
        cellInt = Integer.parseInt(Value);
        drawValueOnGUI();
    }
    public void setValue(int Value){
        cellInt = Value;
        drawValueOnGUI();
    }

    public String getStringValue(){
        return String.valueOf(cellInt);
    }
    public int getIntValue(){
        return cellInt;
    }
    public void setLock(boolean L){
        isLocked = L;
    }
    public boolean isLocked(){
        return isLocked;
    }
    public void drawValueOnGUI(){
        if(cellInt == 0){
            setText("");
        }
        else{
            setText(String.valueOf(cellInt));
        }

    }
}
