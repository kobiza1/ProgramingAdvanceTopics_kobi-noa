package algorithms.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected Queue<AState> toVisit;

    public BreadthFirstSearch(){
        toVisit = new LinkedList<>();
    }

    @Override
    public Solution solve(ISearchable searchable) {
        List<AState> visited = new LinkedList<>();
        List<AState> possibleStates = new LinkedList<>();

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        visited.add(startState);
        toVisit.add(startState);

        while (!toVisit.isEmpty()){
            AState currState = toVisit.poll();

            if(currState == goalState) {
                solution = new Solution(visited);
            }

            possibleStates = searchable.getAllPossibleStats(currState);
            AState nextStat;
            for(int i=0; i<possibleStates.size(); i++){
                nextStat = possibleStates.get(i);
                if(!visited.contains(nextStat)) {
                    visited.add(nextStat);
                    toVisit.add(nextStat);
                }
            }

        }

        List<AState> noPath = new LinkedList<>();
        return new Solution(noPath);
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
