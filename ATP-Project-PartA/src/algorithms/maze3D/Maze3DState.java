package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;

public class Maze3DState extends AState {
    public Maze3DState(int _cost, Object _state, AState _cameFrom, Integer _key) {
        super(_cost, _state, _cameFrom, _key);
    }

    public Maze3DState(int _cost, Object _state, Integer _key) {
        super(_cost, _state, null, _key);
    }

    public Position3D getPosition() {
        return (Position3D)state;
    }

    @Override
    public Maze3DState getCameFrom() {
        return (Maze3DState)cameFrom;
    }

    public String toString(){
        return getPosition().toString();
    }
}
