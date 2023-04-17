package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.*;


public class BreadthFirstSearch extends ASearchingAlgorithm{

    Queue<AState> toVisit;
    boolean breadthOrBest;
    public BreadthFirstSearch(){
        toVisit = new LinkedList<>();
        breadthOrBest = false;
    }

    @Override
    public Solution solve(ISearchable searchable) {
        ArrayList<AState> noPath = new ArrayList<>();
        if(check_inputs(searchable)){
            solution =  new Solution(noPath);
            return solution;
        }

        HashMap<Integer,AState> visited = new HashMap<>();
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
            AState nextState;

            for (AState possibleState : possibleStates) {

                nextState = possibleState;
                Integer key = nextState.getKey();


                if (!visited.containsKey(key)) {
                    visited.put(key, nextState);
                    toVisit.add(nextState);
                }
                else if(visited.get(key).getCost() > possibleState.getCost() && breadthOrBest){
                    visited.put(key, nextState);
                }
            }
        }

        solution = new Solution(noPath);
        nodesEvaluated = visited.size();
        return solution;
    }

    public void addCost(AState currState){
        AState cameFrom = currState.getCameFrom();
        currState.set_cost(cameFrom.getCost() + 1);
    }

    public int get_after_cost_add(AState curState, AState nextState){
        return curState.getCost()+1;
    }

    public Solution backtrackPath(AState stateState,AState goalState){
        ArrayList<AState> path = new ArrayList<>();
        path.add(goalState);
        AState currState = goalState.getCameFrom();
        while (!Objects.equals(currState.getKey(), stateState.getKey())){
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
