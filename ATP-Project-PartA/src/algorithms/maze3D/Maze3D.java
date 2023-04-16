package algorithms.maze3D;

public class Maze3D {
    private int[][][] map;
    private int rowNumber;
    private int columnNumber;
    private int depthNumber;
    private Position3D startPosition;
    private Position3D goalPosition;

    public Maze3D(int[][][] map3D, int rowNumber, int columnNumber, int depthNumber){
        this.map = map3D;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.depthNumber = depthNumber;
        this.startPosition = new Position3D(0,0,0);
        this.goalPosition = new Position3D(0, rowNumber-1, columnNumber-1);
    }

    public int[][][] getMap() {
        return map;
    }

    public Position3D getStartPosition() {
        return startPosition;
    }

    public Position3D getGoalPosition() {
        return goalPosition;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getDepthNumber() {
        return depthNumber;
    }

    public void print(){
        for(int d=0; d<depthNumber; d++) {
            System.out.println("{");
            for (int i = 0; i < rowNumber; i++) {
                System.out.print("[");
                for (int j = 0; j < columnNumber; j++) {
                    if (getStartPosition().getRowIndex() == i && getStartPosition().getColumnIndex() == j)
                        System.out.print("S");
                    else if (getGoalPosition().getRowIndex() == i && getGoalPosition().getColumnIndex() == j)
                        System.out.print("E");
                    else
                        System.out.print(map[d][i][j]);

                    if (j != columnNumber - 1)
                        System.out.print(", ");
                }
                System.out.println("]");
            }
            if (d != depthNumber - 1)
                System.out.print(", ");
            System.out.println("}");
        }
    }
}
