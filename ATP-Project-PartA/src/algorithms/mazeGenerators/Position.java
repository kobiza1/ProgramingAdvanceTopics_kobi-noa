package algorithms.mazeGenerators;

public class Position {
    int RowIndex;
    int ColumnIndex;

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
}
