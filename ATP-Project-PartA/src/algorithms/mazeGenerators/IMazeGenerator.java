package algorithms.mazeGenerators;

public interface IMazeGenerator {
    public Maze generate(int column_num, int row_num);
    public long measureAlgorithmTimeMillis(int column_num, int row_num);
}
