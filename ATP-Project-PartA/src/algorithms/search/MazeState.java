package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * extend AState to maze state with the same functionality but unique to mazes
 */
public class MazeState extends AState{

    public MazeState(int cost, Position position, Integer key) {
        super(cost, position, null, key);
    }

    public MazeState(int cost, Position position, MazeState cameFrom, Integer key) {
        super(cost, position, cameFrom, key);
    }

    public Position getPosition() {
        return (Position)state;
    }

    @Override
    public MazeState getCameFrom() {
        return (MazeState)cameFrom;
    }

    public String toString(){
        return getPosition().toString();
    }
}



