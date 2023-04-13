
package algorithms.mazeGenerators;
public class Maze {

    int[][] maze_board;
    int rows_number;
    int columns_number;

    public Maze(int [][] maze_board, int rows_number, int columns_number){
        this.maze_board = maze_board;
        this.rows_number = rows_number;
        this.columns_number = columns_number;
    }

    public Position getStartPosition(){
        Position startPosition = new Position(0,0);
        return startPosition;
    }

    public Position getGoalPosition(){
        Position GoalPosition = new Position(rows_number,columns_number);
        return GoalPosition;
    }


    public void Print(){
        for(int i=0; i<rows_number; i++){
            for (int j=0; j<columns_number; j++){
                System.out.print(maze_board[i][j] + "  ");
            }
        }
    }
}
