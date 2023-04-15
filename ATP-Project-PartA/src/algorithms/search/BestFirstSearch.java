package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch(){
        Comparator<AState> comp = new AStateComparator();
        toVisit = new PriorityQueue<>(comp);
    }


    public AState addCost(AState currState){
        AState cameFrom = currState.cameFrom;
        currState.add_to_cost(cameFrom.cost);
        return currState;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
