package algorithms.search;

public abstract class AState {

    protected Object state;
    protected AState cameFrom;
    protected int cost;
    protected Integer key;

    public AState(int _cost, Object _state, AState _cameFrom, Integer _key){
        this.cost = _cost;
        this.cameFrom = _cameFrom;
        this.state = _state;
        this.key = _key;
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

    public Integer getKey() {
        return key;
    }

    public int getCost() {
        return cost;
    }

    public Object getState() {
        return state;
    }
}

