package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    private int RowIndex;
    private int ColumnIndex;

    public Position(int rowIndex, int columnIndex){
        RowIndex=rowIndex;
        ColumnIndex=columnIndex;
    }

    public int getRowIndex(){
        return RowIndex;
    }

    public int getColumnIndex(){
        return ColumnIndex;
    }

    public String toString(){
        return "{"+RowIndex+","+ColumnIndex+"}";
    }

    @Override
    public boolean equals(Object position1) {
        /**
         * compare 2 position according to the row_index and the column index
         */
        if(!(position1 instanceof Position)){
            return false;
        }
        Position p1 = (Position) position1;
        return (this.RowIndex == p1.getRowIndex() && this.ColumnIndex == p1.getColumnIndex());
    }
}
