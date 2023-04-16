package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D {
    private int depthIndex;
    private int rowIndex;
    private int columnIndex;

    public Position3D(int depth, int row, int col) {
        depthIndex = depth;
        rowIndex = row;
        columnIndex = col;
    }

    public int getDepthIndex() {
        return depthIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public String toString(){
        return "{"+depthIndex+","+rowIndex+","+columnIndex+"}";
    }

    public boolean equals(Object position1) {
        if(!(position1 instanceof Position3D)){
            return false;
        }
        Position3D p1 = (Position3D) position1;
        return (this.rowIndex == p1.getRowIndex() && this.columnIndex == p1.getColumnIndex() && this.depthIndex == p1.getDepthIndex());
    }
}
