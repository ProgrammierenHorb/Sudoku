package ADT;

import javax.swing.*;

public class SudokuCell extends JTextField {

    private int cellInt;
    private boolean isLocked;

    public SudokuCell(){}
    public SudokuCell(int ValInt, boolean Lock){
        cellInt = ValInt;
        isLocked = Lock;
    }

    public void setValue(String Value){
        cellInt = Integer. parseInt(Value);
    }
    public void setValue(int Value){
        cellInt = Value;
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
}
