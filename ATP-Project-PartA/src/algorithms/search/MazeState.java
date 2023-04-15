package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Comparator;

public class MazeState extends AState{


    public MazeState(int cost, Position position, Integer key) {
        super(cost, position, null, key);
    }

    public MazeState(int cost, Position position, MazeState cameFrom, Integer key) {
        super(cost, position, cameFrom, key);
    }

    @Override
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



