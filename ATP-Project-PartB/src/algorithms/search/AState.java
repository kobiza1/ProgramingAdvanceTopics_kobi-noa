package algorithms.search;

import java.io.Serializable;
import java.util.Comparator;

/**
 * abstract class for states
 */
public abstract class AState implements Serializable {
    public AState(int _cost, Object _state, AState _cameFrom, Integer _key){
        this.cost = _cost;
        this.cameFrom = _cameFrom;
        this.state = _state;
        this.key = _key;
    }

    protected Object state;
    protected AState cameFrom;
    protected int cost;
    protected Integer key;

    public AState getCameFrom() {
        return cameFrom;
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

    public static class AStateComparator implements Comparator<AState> {
        /**
         * comparator class for abstract state
         */

        @Override
        public int compare(AState o1, AState o2) {

            if(o1.cost == o2.cost)
                return 0;
            else if(o1.cost < o2.cost)
                return 1;
            return -1;
        }
    }
}

