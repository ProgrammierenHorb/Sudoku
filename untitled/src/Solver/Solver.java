package Solver;
import ADT.SudokuCell;

public class Solver {
    public Solver() {

        //SudokuCell [][] grid = {{0, 0, 3, 7, 0, 0, 0, 0, 4},
        //               {0, 2, 0, 0, 9, 0, 6, 0, 0},
        //               {8, 0, 1, 0, 0, 0, 0, 0, 0},
        //               {5, 0, 0, 0, 4, 0, 2, 0, 6},
        //               {0, 0, 0, 0, 6, 8, 0, 9, 1},
        //               {6, 0, 0, 0, 0, 2, 0, 7, 0},
        //               {0, 0, 6, 0, 7, 0, 0, 0, 0},
        //               {0, 0, 7, 0, 0, 0, 0, 0, 2},
        //               {2, 0, 8, 0, 3, 0, 0, 1, 9}};


        //print(grid);
        //System.out.println("");
        //System.out.println("-------------------------");
        //System.out.println("");
        //solve(grid);

    }

    public static boolean possible(int n, int y, int x, SudokuCell[][] grid){

        int box_x;
        int box_y;

        for(int horizontal = 0; horizontal < 9; horizontal++){
            if(grid[y][horizontal].getIntValue() == n) return false;
        }

        for(int vertical = 0; vertical < 9; vertical++){
            if(grid[vertical][x].getIntValue() == n) return false;
        }

        box_x = (int) (x / 3) * 3;
        box_y = (int) (y / 3) * 3;

        for(int i = box_x; i < box_x + 3; i++){
            for(int j = box_y; j < box_y + 3; j++){
                if(grid[j][i].getIntValue() == n) return false;
            }
        }

        return true;
    }

    public static SudokuCell[][] solve(SudokuCell[][] grid){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++) {
                if(grid[y][x].getIntValue() == 0){
                    for(int n = 1; n < 10; n++){
                        if(possible(n, y, x, grid)) {
                            grid[y][x].setValue(n);
                            solve(grid);
                            grid[y][x].setValue(0);
                        }
                    }
                    return grid;
                }
            }
        }
        //print(grid);
        return grid;
    }

    public void print(SudokuCell[][] grid){
        System.out.println("");
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                System.out.print(grid[y][x].getIntValue());
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

}
