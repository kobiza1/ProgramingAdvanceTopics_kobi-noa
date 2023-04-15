package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DepthFirstSearch extends ASearchingAlgorithm {

    Map<Integer, Boolean> is_visited;

    public DepthFirstSearch(){
        is_visited = new HashMap<>();
    }
    @Override
    public Solution solve(ISearchable searchable) {
        ArrayList<AState> states = new ArrayList<>();

        AState start_state = searchable.getStartState();
        states.add(start_state);
        is_visited.put(start_state.getKey(), true);
        nodesEvaluated++;
        AState goal_state = searchable.getGoalState();
        try{
            DFS(searchable, start_state, (Position) goal_state.getPosition(), states);}
        catch (Exception e){}
        solution =  new Solution(states);
        return solution;

    }

    public void DFS(ISearchable searchable, AState cur_state, Position goal, ArrayList<AState> solution){

        if(cur_state.getPosition().equals(goal)){
            nodesEvaluated = is_visited.size();;
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
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
