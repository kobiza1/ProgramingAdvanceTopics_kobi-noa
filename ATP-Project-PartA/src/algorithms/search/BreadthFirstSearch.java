package algorithms.search;

import java.util.*;


public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected Queue<AState> toVisit;

    public BreadthFirstSearch(){
        toVisit = new LinkedList<>();
    }

    @Override
    public Solution solve(ISearchable searchable) {
        ArrayList<AState> visited = new ArrayList<>();
        List<AState> possibleStates;

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        visited.add(startState);
        toVisit.add(startState);

        while (!toVisit.isEmpty()){
            AState currState = toVisit.remove();

            if(currState.getPosition().equals(goalState.getPosition())) {
                solution = new Solution(visited);
                return solution;
            }



            possibleStates = searchable.getAllPossibleStates(currState);
            AState nextStat;
            boolean exist = false;
            for(int i=0; i<possibleStates.size(); i++){
                nextStat = possibleStates.get(i);

                for (int j=0; j<visited.size(); j++) {
                    if(visited.get(j).getPosition().equals(nextStat.getPosition())) {
                        exist = true;
                        break;
                    }
                }

                if (!exist) {
                    visited.add(nextStat);
                    toVisit.add(nextStat);
                }
                exist = false;
            }



        }

        ArrayList<AState> noPath = new ArrayList<>();
        return new Solution(noPath);
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
