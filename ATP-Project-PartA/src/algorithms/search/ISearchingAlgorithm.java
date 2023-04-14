package algorithms.search;

public interface ISearchingAlgorithm {
    Solution search(ISearchable searchable);
    String getName();
}
