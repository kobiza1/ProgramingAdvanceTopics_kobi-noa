package algorithms.search;

/**
 * abstract class for searching algorithms
 */

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected Solution solution;
    protected int nodesEvaluated=0;

    public abstract Solution solve(ISearchable searchable);
    public abstract String getName();
    public int getNumberOfNodesEvaluated(){
        return nodesEvaluated;
    }
    public boolean check_inputs(ISearchable s){
        return (s == null);
    }
}
