package algorithms.maze3D;

public class Maze3D {
    private int[][][] map;
    private int rowNumber;
    private int columnNumber;
    private int depthNumber;
    private Position3D startPosition;
    private Position3D goalPosition;

    public void setStartPosition(Position3D startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition(Position3D goalPosition) {
        this.goalPosition = goalPosition;
    }

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
                    if (getStartPosition().getRowIndex() == i && getStartPosition().getColumnIndex() == j && d == getStartPosition().getDepthIndex())
                        System.out.print("S");
                    //else if (getGoalPosition().getRowIndex() == i && getGoalPosition().getColumnIndex() == j && d == getGoalPosition().getDepthIndex())
                       // System.out.print("E");
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

    public void set_value_of_position(Position3D p, int val){
        int row = p.getRowIndex();
        int column = p.getColumnIndex();
        int depth = p.getDepthIndex();
        if(val < 2  && val >=0 && row >=0 && row < rowNumber && column >=0 && column < columnNumber && depth < depthNumber && depth >= 0){
            map[depth][row][column] = val;
        }
    }
    public int get_value_of_position(Position3D p){
        int row = p.getRowIndex();
        int column = p.getColumnIndex();
        int depth = p.getDepthIndex();
        if( row >=0 && row < rowNumber && column >=0 && column < columnNumber && depth < depthNumber && depth >= 0){
            return map[depth][row][column];
        }else{
            return -1;
        }
    }
}
