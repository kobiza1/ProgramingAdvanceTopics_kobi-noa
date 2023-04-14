package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class AState {

    protected Object state;
    protected AState cameFrom;
    protected int cost;

    public AState(int _cost, Object _state, AState _cameFrom){
        this.cost = _cost;
        this.cameFrom = _cameFrom;
        this.state = _state;
    }

    public Object getPosition() {
        return state;
    }

    public AState getCameFrom() {
        return cameFrom;
    }
}
