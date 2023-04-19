package algorithms.search;

/**
 * interface for searching algorithms
 */
public interface ISearchingAlgorithm {
    Solution solve(ISearchable searchable);
    String getName();
    int getNumberOfNodesEvaluated();
}
