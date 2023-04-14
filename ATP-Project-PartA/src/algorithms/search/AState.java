package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class AState {
    private Position position;
    private AState cameFrom;

    public Position getPosition() {
        return position;
    }

    public AState getCameFrom() {
        return cameFrom;
    }
}
