package Generator;
import ADT.SudokuCell;

public class Generator {
    SudokuCell[][] Grid = new SudokuCell[9][9];
    SudokuCell[][] SolvedGrid = new SudokuCell[9][9];

    public Generator(){}
    public SudokuCell[][] GetSolvedGrid(){



        return SolvedGrid;
    }

    public SudokuCell[][] GetGrid(){



        return Grid;
    }
}
