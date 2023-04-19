package algorithms.search;

import java.util.List;

/**
 * interface for searchable classes
 */
public interface ISearchable {
    AState getStartState();
    AState getGoalState();
    List<AState> getAllPossibleStates(AState state);
}
