package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D{
    protected int m_row;
    protected int m_col;
    protected int m_depth;
    protected Maze3D m_maze;
    @Override
    public  abstract Maze3D generate(int depth, int row, int column);

    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) {
        long start_time = System.currentTimeMillis();
        generate(depth, row, column);
        long end_time = System.currentTimeMillis();
        return (end_time-start_time);
    }

    public boolean check_inputs(int d, int r, int c){
        return (d <= 0 || r <= 0 || c <= 0);
    }
}
