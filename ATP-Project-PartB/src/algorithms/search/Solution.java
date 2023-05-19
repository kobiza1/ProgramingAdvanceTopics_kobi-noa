package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * hold the solution of the mazes
 */
public class Solution implements Serializable {
    private ArrayList<AState> solution;
    public Solution(ArrayList<AState> path){
        solution=path;
    }
    public ArrayList<AState> getSolutionPath() {
        return solution;
    }
}
