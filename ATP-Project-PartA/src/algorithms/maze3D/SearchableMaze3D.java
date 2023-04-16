package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.*;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze3D;
    private int columnNumber;
    private int rowNumber;
    private int depthNumber;

    public SearchableMaze3D(Maze3D maze3D){
        this.maze3D = maze3D;
        this.columnNumber = maze3D.getColumnNumber();
        this.rowNumber = maze3D.getRowNumber();
        this.depthNumber = maze3D.getDepthNumber();
    }
    @Override
    public AState getStartState() {
        Position3D position3D = maze3D.getStartPosition();
        return new Maze3DState(1,position3D,0);
    }

    @Override
    public AState getGoalState() {
        Position3D position3D = maze3D.getGoalPosition();
        Integer key=0;
        return new Maze3DState(2147483647,position3D,key);
    }

    @Override
    public List<AState> getAllPossibleStates(AState state) {
        List<AState> all_possible_states = new ArrayList<>();

        Maze3DState currState = (Maze3DState) state;
        Position3D currPosition3D = (Position3D) state.getState();
        int[][][] map3D = maze3D.getMap();
        int row = currPosition3D.getRowIndex();
        int col = currPosition3D.getColumnIndex();
        int depth = currPosition3D.getDepthIndex();

        Position3D nextPosition;
        Integer key;
        // Up
        if(row != 0 && map3D[depth][row-1][col] == 0) {
            nextPosition = new Position3D(depth, row - 1, col);
            key = getKey(nextPosition);
            all_possible_states.add(new Maze3DState(1, nextPosition, currState, key));
        }

        // Down
        if(row != columnNumber - 1 && map3D[depth][row+1][col] == 0) {
            nextPosition = new Position3D(depth, row+1, col);
            key = getKey(nextPosition);
            all_possible_states.add(new Maze3DState(1, nextPosition, currState, key));
        }

        // Left
        if(col !=0 && map3D[depth][row][col-1] == 0){
            nextPosition = new Position3D(depth, row,col-1);
            key = getKey(nextPosition);
            all_possible_states.add(new Maze3DState(1, nextPosition, currState, key));
        }

        // Right
        if(col != rowNumber - 1 && map3D[depth][row][col+1] == 0){
            nextPosition = new Position3D(depth, row,col+1);
            key = getKey(nextPosition);
            all_possible_states.add(new Maze3DState(1, nextPosition, currState, key));
        }

        // Inward
        if(depth != depthNumber - 1 && map3D[depth+1][row][col] == 0){
            nextPosition = new Position3D(depth+1, row,col);
            key = getKey(nextPosition);
            all_possible_states.add(new Maze3DState(1, nextPosition, currState, key));
        }

        // Outwardly
        if(depth != 0 && map3D[depth-1][row][col] == 0){
            nextPosition = new Position3D(depth-1, row,col);
            key = getKey(nextPosition);
            all_possible_states.add(new Maze3DState(1, nextPosition, currState, key));
        }
        return all_possible_states;
    }

    public Integer getKey(Position3D position3D){
        int row = position3D.getRowIndex();
        int col = position3D.getColumnIndex();
        int depth = position3D.getDepthIndex();

        return col+row*columnNumber+depth*rowNumber*columnNumber;
    }
}
