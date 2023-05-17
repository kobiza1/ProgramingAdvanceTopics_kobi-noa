package algorithms.search;

import java.util.*;

/**
 * search the extends BFS but use priority queue to evaluate fewer states
 */
public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch(){
        Comparator<AState> comp =  new AState.AStateComparator();
        toVisit = new PriorityQueue<>(comp);
        breadthOrBest = true;
    }
    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
