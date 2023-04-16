package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D{
    @Override
    public  abstract Maze3D generate(int depth, int row, int column);

    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long start_time = System.currentTimeMillis();
        generate(depth, row, column);
        long end_time = System.currentTimeMillis();
        return (end_time-start_time);
    }
}
