package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{


    public MazeState(int cost, Position position) {
        super(cost, position, null);
    }

    public MazeState(int cost, Position position, MazeState cameFrom) {
        super(cost, position, cameFrom);
    }

    @Override
    public Position getPosition() {
        return (Position)state;
    }

    @Override
    public MazeState getCameFrom() {
        return (MazeState)cameFrom;
    }

}
