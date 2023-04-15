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
        int columns = searchable.getSize()[1];
        int rows = searchable.getSize()[0];
        for(int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                Integer cell_number = i*columns + j;
                is_visited.put(cell_number, false);
            }
        }
        AState start_state = searchable.getStartState();
        states.add(start_state);
        Integer start_position = ((Position)start_state.getPosition()).getRowIndex()*columns + ((Position) start_state.getPosition()).getColumnIndex();
        is_visited.put(start_position, true);
        AState goal_state = searchable.getGoalState();
        Integer goal_position = ((Position)goal_state.getPosition()).getRowIndex()*columns + ((Position) goal_state.getPosition()).getColumnIndex();
        try{
            DFS(searchable, start_state, (Position) goal_state.getPosition(), states);}
        catch (Exception e){

        }
        solution =  new Solution(states);
        return solution;

    }

    public void DFS(ISearchable searchable, AState cur_state, Position goal, ArrayList<AState> solution){

        if(cur_state.getPosition().equals(goal)){
            throw new RuntimeException();
        }
        Position next_position;
        Integer position;
        for(AState state : searchable.getAllPossibleStates(cur_state)){
            next_position = (Position) state.getPosition();
            position = next_position.getRowIndex()*searchable.getSize()[1] + next_position.getColumnIndex();
            if(!is_visited.get(position)){
                solution.add(state);
                is_visited.put(position, true);
                DFS(searchable, state, goal, solution);
            }
        }

    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
