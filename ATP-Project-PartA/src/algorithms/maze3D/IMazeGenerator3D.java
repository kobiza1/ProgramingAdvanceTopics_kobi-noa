package algorithms.maze3D;

/**
 * interface for maze 3D generators
 */

public interface IMazeGenerator3D {
    Maze3D generate(int depth, int row, int column);
    long measureAlgorithmTimeMillis(int depth, int row, int column);

}
