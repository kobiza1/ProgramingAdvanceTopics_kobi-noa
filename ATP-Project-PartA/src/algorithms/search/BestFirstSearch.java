package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch(){}

    public void addCost(AState currState){
        AState cameFrom = currState.getCameFrom();
        currState.add_to_cost(cameFrom.getCost());
    }

    public int get_after_cost_add(AState curState, AState nextState){
        return curState.getCost()+nextState.getCost();
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
