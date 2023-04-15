package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;


public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected Queue<AState> toVisit;

    public BreadthFirstSearch(){
        Comparator<AState> comp = new AStateComparator();
        toVisit = new PriorityQueue<>(comp);
    }

    @Override
    public Solution solve(ISearchable searchable) {
        HashMap<Integer,AState> visited = new HashMap<Integer, AState>();
        List<AState> possibleStates;
        Integer[] size = searchable.getSize();
        int colNumber = size[1];

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        startState.set_cost(0);
        visited.put(0,startState);
        toVisit.add(startState);

        while (!toVisit.isEmpty()){
            AState currState = toVisit.remove();

            if(currState.getPosition().equals(goalState.getPosition())) {
                solution = backtrackPath(startState,currState);
                nodesEvaluated = visited.size();
                return solution;
            }

            possibleStates = searchable.getAllPossibleStates(currState);
            AState nextStat;

            for(int i=0; i<possibleStates.size(); i++){

                nextStat = possibleStates.get(i);
                Position p = (Position)nextStat.getPosition();
                Integer key = p.getRowIndex()*colNumber + p.getColumnIndex();

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
        AState cameFrom = currState.cameFrom;
        currState.set_cost(cameFrom.cost + 1);
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
