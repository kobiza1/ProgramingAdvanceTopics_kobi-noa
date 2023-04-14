package algorithms.search;

import java.util.List;

public class SearchableMaze implements ISearchable{
    @Override
    public AState getStartState() {
        return null;
    }

    @Override
    public AState getGoalState() {
        return null;
    }

    @Override
    public List<AState> getAllPossibleStats(AState state) {
        return null;
    } // TODO: object adapter between Maze and ISearchAble
}
