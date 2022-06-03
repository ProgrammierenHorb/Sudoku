package ADT;

public class SudokuCell {

    private String CellString;
    private int CellInt;
    private boolean IsLocked;

    public SudokuCell(){}
    public SudokuCell(int ValInt, String ValStr, boolean Lock){
        CellString = ValStr;
        CellInt = ValInt;
        IsLocked = Lock;
    }

    public void SetValue(String Value){
        CellString = Value;
        CellInt = Integer. parseInt(Value);

    }
    public void SetValue(int Value){
        CellInt = Value;
        CellString = Integer.toString(Value) ;
    }
    public String GetStringValue(){
        return CellString;
    }
    public int GetIntValue(){
        return CellInt;
    }
    public void SetLock(boolean L){
        IsLocked = L;
    }
    public boolean IsLocked(){
        return IsLocked;
    }
}
