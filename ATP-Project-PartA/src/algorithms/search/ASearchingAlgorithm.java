package algorithms.search;

import algorithms.mazeGenerators.Maze;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected Solution solution;
    protected int nodesEvaluated=0;

    public abstract Solution solve(ISearchable searchable);
    public abstract String getName();
    public int getNumberOfNodesEvaluated(){
        return nodesEvaluated;
    }
}
