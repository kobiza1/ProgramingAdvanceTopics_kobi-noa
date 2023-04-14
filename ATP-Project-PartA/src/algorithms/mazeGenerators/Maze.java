
package algorithms.mazeGenerators;
public class Maze {

    int[][] maze_board;
    int rows_number;
    int columns_number;

    Position StartPosition;
    Position GoalPosition;

    public Maze(int [][] maze_board, int rows_number, int columns_number){
        this.maze_board = maze_board;
        this.rows_number = rows_number;
        this.columns_number = columns_number;
        this.GoalPosition = new Position(rows_number-1,columns_number-1);
        this.StartPosition = new Position(0,0);
    }

    public Position getStartPosition(){
        return StartPosition;
    }

    public Position getGoalPosition(){
        return GoalPosition;
    }


    public void Print(){
        System.out.println("S="+getStartPosition()+" D="+getGoalPosition());
        for(int i=0; i<rows_number; i++){
            for (int j=0; j<columns_number; j++){
                System.out.print(maze_board[i][j] + "");
            }
            System.out.println();
        }
    }


    public void set_value_of_position(int row, int col, int val){
        if(row >=0 && row < maze_board.length && col >=0 && col < maze_board[0].length)
            this.maze_board[row][col] = val;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    public int get_value_of_position(int row, int col){
        if(row>=0 && row<this.maze_board.length && col>=0 && col < this.maze_board[0].length){
            return maze_board[row][col];
        }else{
            return -1;
        }

    }
}
