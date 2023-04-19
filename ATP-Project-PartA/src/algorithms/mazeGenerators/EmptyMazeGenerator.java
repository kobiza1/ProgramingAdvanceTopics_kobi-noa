package algorithms.mazeGenerators;

import java.util.Map;

public class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * create an empty maze, maze that contain only 0's
     */
    public Maze generate(int columns_num, int rows_num){
        int[][] maze_board = new int[rows_num][columns_num];
        if(check_inputs(rows_num, columns_num)){
            return new Maze(maze_board, rows_num, columns_num);
        }
        for(int i=0; i<rows_num; i++){
            for (int j=0; j<columns_num; j++){
                maze_board[i][j] = 0;
            }
        }
        return new Maze(maze_board, rows_num, columns_num);
    }
}
