package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Comparator;

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

    public void add_to_cost(int cost_to_add){
        this.cost += cost_to_add;
    }

    public void set_cost(int new_cost){
        this.cost = new_cost;
    }

}

