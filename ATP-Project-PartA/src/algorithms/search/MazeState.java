package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position position;
    private MazeState cameFrom;
    public MazeState(int RowIndex, int ColumnIndex) {
        position = new Position(RowIndex, ColumnIndex);
        cameFrom = null;
    }

    public MazeState(int RowIndex, int ColumnIndex, MazeState _cameFrom) {
        position = new Position(RowIndex, ColumnIndex);
        cameFrom = _cameFrom;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public AState getCameFrom() {
        return cameFrom;
    }
}
