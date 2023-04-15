package algorithms.search;

import algorithms.mazeGenerators.Position;

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
        sol.add(start_state);
        is_visited.put(start_state.getKey(), true);
        AState goal_state = searchable.getGoalState();
        AState cur_state = start_state;
        nodesEvaluated++;
        int counter;
        while (!Objects.equals(cur_state.key, goal_state.getKey()) && !sol.empty()) {
            counter = 0;
            cur_state = sol.peek();
            for (AState state : searchable.getAllPossibleStates(cur_state)) {
                Integer key = state.getKey();
                if (!is_visited.containsKey(key) || !is_visited.get(key)) {
                    counter++;
                    nodesEvaluated++;
                    sol.add(state);
                    is_visited.put(key, true);
                }
            }
            if(counter == 0){
                sol.pop();
            }
        }
        sol.add(cur_state);
        states =  new ArrayList(sol);
        solution = new Solution(states);
        return solution;
    }
       /* try{
            DFS(searchable, start_state, goal_state, states);}
        catch (Exception e){}
        solution =  new Solution(states);
        return solution;

    }

    public void DFS(ISearchable searchable, AState cur_state, AState goal, ArrayList<AState> solution){

        if(Objects.equals(cur_state.getKey(), goal.getKey())){
            throw new RuntimeException();
        }

        for(AState state : searchable.getAllPossibleStates(cur_state)){
            Integer key = state.getKey();
            if(!is_visited.containsKey(key)) {
                    solution.add(state);
                    is_visited.put(key, true);
                    DFS(searchable, state, goal, solution);
            }
        }
    }*/

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
