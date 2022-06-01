package Generator;
import ADT.SudokuCell;

public class Generator {

    public Generator(){}
    public SudokuCell[][] GetSolvedGrid(){
        SudokuCell[][] SolvedGrid = new SudokuCell[9][9];
        for(int i = 0 ; i<9; i++){
            for(int j = 0 ; j<9; j++){
                SolvedGrid[i][j] = new SudokuCell(0,"0",false);
            }
        }

        return SolvedGrid;
    }

    public SudokuCell[][] GetSolvableGrid(SudokuCell[][] FullGrid){
        SudokuCell[][] Grid = new SudokuCell[9][9];
        FillGrid(Grid);
        return Grid;
    }

    public SudokuCell[][] FillGrid(SudokuCell[][] Grid){
        //Grid[row][col]
        for(int col = 0 ; col<9; col++){
            for(int row = 0 ; row<9; row++) {
                if (Grid[row][col].GetIntValue() == 0) {
                    boolean End = false;
                    do{
                        int RandNum = (int) (Math.random() * 9 + 1);
                        boolean IsOK = true;
                        //check row
                        if (IsOK == true) {
                            for (int i = 0; i < 9; i++) {
                                if (Grid[row][i].GetIntValue() == RandNum) {
                                    IsOK = false;
                                    break;
                                }
                            }
                        }
                        //check column
                        if (IsOK == true) {
                            for (int i = 0; i < 9; i++) {
                                if (Grid[row][i].GetIntValue() == RandNum) {
                                    IsOK = false;
                                    break;
                                }
                            }
                        }
                        //check square
                        if (IsOK == true) {
                            //get square number
                            double r = row / 3;
                            double c = col / 3;

                            if (r < 1) {
                                if (c < 1) {
                                    for (int rr = 0; rr < 3; rr++) {
                                        for (int cc = 0; cc < 3; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 2) {
                                    for (int rr = 0; rr < 3; rr++) {
                                        for (int cc = 3; cc < 6; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 3) {
                                    for (int rr = 0; rr < 3; rr++) {
                                        for (int cc = 6; cc < 9; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (r < 2) {
                                if (c < 1) {
                                    for (int rr = 3; rr < 6; rr++) {
                                        for (int cc = 0; cc < 3; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 2) {
                                    for (int rr = 3; rr < 6; rr++) {
                                        for (int cc = 3; cc < 6; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 3) {
                                    for (int rr = 3; rr < 6; rr++) {
                                        for (int cc = 6; cc < 9; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (r < 3) {
                                if (c < 1) {
                                    for (int rr = 6; rr < 9; rr++) {
                                        for (int cc = 0; cc < 3; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 2) {
                                    for (int rr = 6; rr < 9; rr++) {
                                        for (int cc = 3; cc < 6; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 3) {
                                    for (int rr = 6; rr < 9; rr++) {
                                        for (int cc = 6; cc < 9; cc++) {
                                            if (Grid[rr][cc].GetIntValue() == RandNum) {
                                                IsOK = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (IsOK = true) {
                            Grid[row][col].SetValue(RandNum);
                            End = true;
                        }
                    }while(End == false);
                }
            }

        }
        return Grid;
    }
}