package algorithms.search;

import algorithms.mazeGenerators.Maze;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected ISearchable searchable;
    protected Solution solution;

    public abstract Solution solve(ISearchable searchable);
    public abstract String getName();
}
