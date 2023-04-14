package algorithms.mazeGenerators;

public interface IMazeGenerator {
    Maze generate(int column_num, int row_num);
    long measureAlgorithmTimeMillis(int column_num, int row_num);
}
