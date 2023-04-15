package algorithms.search;

import algorithms.mazeGenerators.Maze;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected Solution solution;

    public abstract Solution solve(ISearchable searchable);
    public abstract String getName();
    public int getNumberOfNodesEvaluated(){
        return solution.getSolutionPath().size();
    }
}
