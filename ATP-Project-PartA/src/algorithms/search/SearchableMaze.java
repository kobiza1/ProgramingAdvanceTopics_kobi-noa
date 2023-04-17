package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable{

    private Maze m_maze;
    public SearchableMaze(Maze maze){
        m_maze = maze;
    }

    public AState getStartState(){
        Position start_position = m_maze.getStartPosition();
        return new MazeState(0, start_position,0);
    }
    public AState getGoalState(){
        Position goal_position = m_maze.getGoalPosition();
        Integer key = goal_position.getRowIndex()*m_maze.getColumns_number() + goal_position.getColumnIndex();
        return new MazeState(2147483647, goal_position, key);
    }

    public List<AState> getAllPossibleStates(AState m_state){

        List<AState> all_possible_states = new ArrayList<>();
        Position next_position;
        int[][] maze_board = m_maze.getMaze_board();
        MazeState cur_state = (MazeState)m_state;
        int row = cur_state.getPosition().getRowIndex();
        int column = cur_state.getPosition().getColumnIndex();

        // case 1: move right and down (slant)
        if(column != (maze_board[0].length - 1) && row != (maze_board.length-1) && maze_board[row+1][column+1] == 0
                && (maze_board[row+1][column] == 0 || maze_board[row][column+1] == 0)){
            next_position = new Position(row + 1, column + 1);
            Integer key = next_position.getRowIndex()*m_maze.getColumns_number() + next_position.getColumnIndex();
            all_possible_states.add(new MazeState(cur_state.getCost() + 15, next_position, cur_state, key));
        }
        // case 2 : move right and up (slant)
        if(column != (maze_board[0].length - 1) && row != 0 && maze_board[row-1][column+1] == 0
                && (maze_board[row-1][column] == 0 || maze_board[row][column+1] == 0)){
            next_position = new Position(row - 1, column + 1);
            Integer key = next_position.getRowIndex()*m_maze.getColumns_number() + next_position.getColumnIndex();
            all_possible_states.add(new MazeState(cur_state.getCost() + 15, next_position, cur_state, key));
        }
        // case 3 : move left and up (slant)
        if(column != 0 && row != 0 && maze_board[row-1][column-1] == 0
                && (maze_board[row-1][column] == 0 || maze_board[row][column-1] == 0)){
            next_position = new Position(row - 1, column - 1);
            Integer key = next_position.getRowIndex()*m_maze.getColumns_number() + next_position.getColumnIndex();
            all_possible_states.add(new MazeState(cur_state.getCost() + 15, next_position, cur_state, key));
        }
        // case 4 : move left and down (slant)
        if(column != 0 && row != (maze_board.length-1) && maze_board[row+1][column-1] == 0
                && (maze_board[row+1][column] == 0 || maze_board[row][column-1] == 0)){
            next_position = new Position(row + 1, column - 1);
            Integer key = next_position.getRowIndex()*m_maze.getColumns_number() + next_position.getColumnIndex();
            all_possible_states.add(new MazeState(cur_state.getCost() + 15, next_position, cur_state, key));
        }
        // case 5 : move right
        if((column != (maze_board[0].length - 1)) && maze_board[row][column+1] == 0){
            next_position = new Position(row, column+1);
            Integer key = next_position.getRowIndex()*m_maze.getColumns_number() + next_position.getColumnIndex();
            all_possible_states.add(new MazeState(cur_state.getCost() + 10, next_position, cur_state, key));
        }
        // case 6 : move left
        if(column != 0 && maze_board[row][column-1] == 0){
            next_position = new Position(row, column-1);
            Integer key = next_position.getRowIndex()*m_maze.getColumns_number() + next_position.getColumnIndex();
            all_possible_states.add(new MazeState(cur_state.getCost() + 10, next_position, cur_state, key));
        }
        // case 7 : move up
        if(row != 0 && maze_board[row-1][column] == 0){
            next_position = new Position(row - 1, column);
            Integer key = next_position.getRowIndex()*m_maze.getColumns_number() + next_position.getColumnIndex();
            all_possible_states.add(new MazeState(cur_state.getCost() + 10, next_position, cur_state, key));
        }
        // case 8 : move down
        if(row != (maze_board.length - 1) && maze_board[row+1][column] == 0){
            next_position = new Position(row + 1, column);
            Integer key = next_position.getRowIndex()*m_maze.getColumns_number() + next_position.getColumnIndex();
            all_possible_states.add(new MazeState(cur_state.getCost() + 10, next_position, cur_state, key));
        }

        return all_possible_states;
    }
}
