package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch(){}

    public AState addCost(AState currState){
        AState cameFrom = currState.getCameFrom();
        currState.add_to_cost(cameFrom.getCost());
        return currState;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
