package algorithms.search;

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
        if(check_inputs(searchable)){ // if we get null
            solution =  new Solution(noPath);
            return solution;
        }

        HashMap<Integer,AState> visited = new HashMap<>();
        List<AState> possibleStates;

        AState startState = searchable.getStartState();
        AState goalState = searchable.getGoalState();

        startState.set_cost(0); // start state have no cost
        visited.put(startState.getKey(),startState); // add start state to the visited list
        toVisit.add(startState); // add start state to the queue

        while (!toVisit.isEmpty()){
            AState currState = toVisit.remove();

            // if we are in the goal we're done
            if(Objects.equals(currState.getKey(), goalState.getKey())) {
                solution = backtrackPath(startState,currState);
                return solution;
            }

            possibleStates = searchable.getAllPossibleStates(currState); // get all the next steps that possible
            AState nextState;
            nodesEvaluated++;

            for (AState possibleState : possibleStates) {


                nextState = possibleState;
                Integer key = nextState.getKey();


                if (!visited.containsKey(key)) { // check if we visit this state
                    visited.put(key, nextState);
                    toVisit.add(nextState);
                }

                else if(visited.get(key).getCost() > possibleState.getCost() && breadthOrBest){ // if the cost is cheaper change the cost of this state
                    visited.put(key, nextState);
                }
            }
        }

        solution = new Solution(noPath);
        return solution;
    }


    public Solution backtrackPath(AState stateState,AState goalState){
        /**
         * backtrack the solution from the goal to the start
         */
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
