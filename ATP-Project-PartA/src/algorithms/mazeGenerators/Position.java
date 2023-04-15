package algorithms.mazeGenerators;

public class Position {
    int RowIndex;
    int ColumnIndex;
    boolean visited;

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

    public boolean gotVisited(){
        return visited;
    }

    public void  set_visited(){
        this.visited = true;
    }

    @Override
    public boolean equals(Object position1) {
        if(!(position1 instanceof Position)){
            return false;
        }
        Position p1 = (Position) position1;
        return (this.RowIndex == p1.getRowIndex() && this.ColumnIndex == p1.getColumnIndex());
    }
}
