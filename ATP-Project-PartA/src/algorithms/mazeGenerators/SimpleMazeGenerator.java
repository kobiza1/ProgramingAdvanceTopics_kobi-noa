package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int columns_num, int rows_num) {
        int zeros_or_ones, random_break;
        Random r = new Random();
        int [][] maze = new int[rows_num][columns_num];
        for (int i=0; i<rows_num; i++){
            zeros_or_ones = r.nextInt(2);
            for (int j=0; j<columns_num; j++){
                random_break = r.nextInt(columns_num);
                if(zeros_or_ones == 1 && random_break != j){
                    maze[i][j] = 1;
                }
                else {
                    maze[i][j] = 0;
                }
            }
        }

        maze[0][0]=0;
        maze[rows_num-1][columns_num-1]=0;

        return new Maze(maze, rows_num, columns_num);
    }
}
