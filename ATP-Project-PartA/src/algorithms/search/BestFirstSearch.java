package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch(){
        toVisit = new PriorityQueue<>();
    }

    @Override
    public Solution solve(ISearchable searchable) {
        return null;
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
