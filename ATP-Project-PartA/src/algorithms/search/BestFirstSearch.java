package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BestFirstSearch extends ASearchingAlgorithm{
    @Override
    public Solution search(Maze maze) {
        return null;
    }

    public Solution FindPath(Integer CurrState){
        List<MazeState> solution = new LinkedList<>();
        MazeState start = new MazeState(0,0);
        solution.add(start);


        return new Solution(solution);
    }
}
