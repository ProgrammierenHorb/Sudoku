package Killer;

import Sudoku.SudokuCell;

public class KillerCell extends SudokuCell {

    boolean inSum;
    int sumKey;

    public KillerCell(int[] position){
        super(position);
        inSum = false;
        sumKey = -1;
    }

    public KillerCell(){
        super();
        inSum = false;
        sumKey = -1;
    }

    public int getSumKey(){
        return sumKey;
    }

    public void setSumKey(int sumKey){
        this.sumKey = sumKey;
    }

}
