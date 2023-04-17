package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch(){
        Comparator<AState> comp = new AStateComparator();
        toVisit = new PriorityQueue<>(comp);
        breadthOrBest = true;
    }
    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
