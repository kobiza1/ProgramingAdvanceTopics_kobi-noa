package algorithms.search;

import java.util.ArrayList;

/**
 * hold the solution of the mazes
 */
public class Solution {
    private ArrayList<AState> solution;

    public Solution(ArrayList<AState> path){
        solution=path;
    }
    public ArrayList<AState> getSolutionPath() {
        return solution;
    }
}
