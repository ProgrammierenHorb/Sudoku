package Generator;
import ADT.SudokuCell;

public class Generator {

    public Generator(){}
    public SudokuCell[][] getSolvedGrid(){
        SudokuCell[][] solvedGrid = new SudokuCell[9][9];
        for(int i = 0 ; i<9; i++){
            for(int j = 0 ; j<9; j++){
                solvedGrid[i][j] = new SudokuCell(0,false);
            }
        }

        return solvedGrid;
    }

    public SudokuCell[][] getSolvableGrid(SudokuCell[][] FullGrid){
        SudokuCell[][] grid = new SudokuCell[9][9];
        fillGrid(grid);
        return grid;
    }

    public SudokuCell[][] fillGrid(SudokuCell[][] grid){
        //Grid[row][col]
        for(int col = 0 ; col<9; col++){
            for(int row = 0 ; row<9; row++) {
                if (grid[row][col].getIntValue() == 0) {
                    boolean end = false;
                    do{
                        int randNum = (int) (Math.random() * 9 + 1);
                        boolean isOK = true;
                        //check row
                        if (isOK == true) {
                            for (int i = 0; i < 9; i++) {
                                if (grid[row][i].getIntValue() == randNum) {
                                    isOK = false;
                                    break;
                                }
                            }
                        }
                        //check column
                        if (isOK == true) {
                            for (int i = 0; i < 9; i++) {
                                if (grid[row][i].getIntValue() == randNum) {
                                    isOK = false;
                                    break;
                                }
                            }
                        }
                        //check square
                        if (isOK == true) {
                            //get square number
                            double r = row / 3;
                            double c = col / 3;

                            if (r < 1) {
                                if (c < 1) {
                                    for (int rr = 0; rr < 3; rr++) {
                                        for (int cc = 0; cc < 3; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 2) {
                                    for (int rr = 0; rr < 3; rr++) {
                                        for (int cc = 3; cc < 6; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 3) {
                                    for (int rr = 0; rr < 3; rr++) {
                                        for (int cc = 6; cc < 9; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (r < 2) {
                                if (c < 1) {
                                    for (int rr = 3; rr < 6; rr++) {
                                        for (int cc = 0; cc < 3; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 2) {
                                    for (int rr = 3; rr < 6; rr++) {
                                        for (int cc = 3; cc < 6; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 3) {
                                    for (int rr = 3; rr < 6; rr++) {
                                        for (int cc = 6; cc < 9; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (r < 3) {
                                if (c < 1) {
                                    for (int rr = 6; rr < 9; rr++) {
                                        for (int cc = 0; cc < 3; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 2) {
                                    for (int rr = 6; rr < 9; rr++) {
                                        for (int cc = 3; cc < 6; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                } else if (c < 3) {
                                    for (int rr = 6; rr < 9; rr++) {
                                        for (int cc = 6; cc < 9; cc++) {
                                            if (grid[rr][cc].getIntValue() == randNum) {
                                                isOK = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (isOK = true) {
                            grid[row][col].setValue(randNum);
                            end = true;
                        }
                    }while(end == false);
                }
            }

        }
        return grid;
    }
}