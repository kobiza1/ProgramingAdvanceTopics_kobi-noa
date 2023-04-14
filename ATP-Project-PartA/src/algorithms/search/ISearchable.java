package algorithms.search;

import java.util.List;

public interface ISearchable {
    MazeState getStartState();
    MazeState getGolState();
    List<MazeState> getAllSuccessors(MazeState state);
}
