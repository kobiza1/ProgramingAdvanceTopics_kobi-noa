package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;


public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch(){}

    @Override
    public Solution solve(ISearchable searchable) {
        Comparator<AState> comp = new AStateComparator();
        Queue<AState> toVisit = new PriorityQueue<>(comp);
        HashMap<Integer,AState> visited = new HashMap<Integer, AState>();
        List<AState> possibleStates;

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        startState.set_cost(0);
        visited.put(startState.getKey(),startState);
        toVisit.add(startState);

        while (!toVisit.isEmpty()){
            AState currState = toVisit.remove();

            if(Objects.equals(currState.getKey(), goalState.getKey())) {
                solution = backtrackPath(startState,currState);
                nodesEvaluated = visited.size();
                return solution;
            }

            possibleStates = searchable.getAllPossibleStates(currState);
            AState nextStat;

            for(int i=0; i<possibleStates.size(); i++){

                nextStat = possibleStates.get(i);
                Integer key = nextStat.getKey();

                if(!visited.containsKey(key)){
                    visited.put(key, nextStat);
                    addCost(nextStat);
                    toVisit.add(nextStat);
                }
            }
        }
        ArrayList<AState> noPath = new ArrayList<>();
        solution = new Solution(noPath);
        return solution;
    }

    public AState addCost(AState currState){
        AState cameFrom = currState.getCameFrom();
        currState.set_cost(cameFrom.getCost() + 1);
        return currState;
    }

    public Solution backtrackPath(AState stateState,AState goalState){
        ArrayList<AState> path = new ArrayList<>();
        path.add(goalState);
        AState currState = goalState.getCameFrom();
        while (!currState.getPosition().equals(stateState.getPosition())){
            path.add(currState);
            currState = currState.getCameFrom();
        }
        path.add(currState);
        Collections.reverse(path);
        return new Solution(path);
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
