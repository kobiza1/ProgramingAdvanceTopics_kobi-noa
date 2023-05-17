package algorithms.mazeGenerators;

/**
 * abstract class that gather all the Maze Generators
 */

public abstract class AMazeGenerator implements IMazeGenerator {

    protected  int number_of_rows;
    protected  int number_of_columns;

    public abstract Maze generate(int columns_num, int rows_num);
    public long measureAlgorithmTimeMillis(int columns_num, int rows_num){
        /**
         * measure the time that take to the generate function to create maze in ms
         */
        long start_time = System.currentTimeMillis();
        generate(columns_num, rows_num);
        long end_time = System.currentTimeMillis();
        return (end_time-start_time);
    }

    public boolean check_inputs(int rows, int cols){
        return (rows <= 0 || cols <= 0);
    }
}
