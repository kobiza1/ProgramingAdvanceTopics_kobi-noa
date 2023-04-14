package algorithms.search;

import java.util.List;

public class Solution {
    private List<AState> solution;

    public Solution(List<AState> path){
        solution=path;
    }

    public List<AState> getSolution() {
        return solution;
    }
}
