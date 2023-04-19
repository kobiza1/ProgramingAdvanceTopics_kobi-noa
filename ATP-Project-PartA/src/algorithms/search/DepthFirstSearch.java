package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithm {

    Map<Integer, Boolean> is_visited;
    Stack<AState> sol;

    public DepthFirstSearch(){
        is_visited = new HashMap<>();
        sol = new Stack<>();
    }
    @Override

    public Solution solve(ISearchable searchable) {
        ArrayList<AState> states;

        AState start_state = searchable.getStartState();
        // add start state to the solution and to the visited
        sol.add(start_state);
        is_visited.put(start_state.getKey(), true);
        AState goal_state = searchable.getGoalState();
        AState cur_state = start_state;
        nodesEvaluated++;
        int counter;
        while (!Objects.equals(cur_state.key, goal_state.getKey()) && !sol.empty()) { // while we didn't in the goal, or we can't get to the goal
            counter = 0;
            cur_state = sol.peek();
            for (AState state : searchable.getAllPossibleStates(cur_state)) { // get all the possible steps
                Integer key = state.getKey();
                if (!is_visited.containsKey(key) || !is_visited.get(key)) { // if we didn't got there yet
                    counter++;
                    nodesEvaluated++;
                    sol.add(state); // add to solution
                    is_visited.put(key, true); // is visited
                }
            }
            if(counter == 0){
                sol.pop();
            }
        }
        if(sol.size() != 0){ // add the last node
            sol.add(cur_state);
        }
        states =  new ArrayList(sol);
        solution = new Solution(states);
        return solution;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
