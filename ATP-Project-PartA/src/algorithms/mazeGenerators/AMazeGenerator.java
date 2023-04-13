package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    public abstract Maze generate(int columns_num, int rows_num);

    public long measureAlgorithmTimeMillis(int columns_num, int rows_num){
        long start_time = System.currentTimeMillis();
        generate(columns_num, rows_num);
        long end_time = System.currentTimeMillis();
        return (end_time-start_time);
    }
}
