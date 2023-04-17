package test;
import algorithms.mazeGenerators.IMazeGenerator; import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator; import algorithms.search.*;
import java.util.ArrayList;
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        boolean check = true;
//        solveProblem(searchableMaze, new BreadthFirstSearch()); solveProblem(searchableMaze, new DepthFirstSearch()); solveProblem(searchableMaze, new BestFirstSearch());
        while (check)
        {

            Maze maze = mg.generate(1000, 1000);
            System.out.println(String.format("Maze generation time(ms): %s", mg.measureAlgorithmTimeMillis(1000/*rows*/,1000/*columns*/)));
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            check = solveProblem(searchableMaze, new BreadthFirstSearch());
            check = check && solveProblem(searchableMaze, new DepthFirstSearch());
            check = check && solveProblem(searchableMaze, new BestFirstSearch());
        }
    }
    private static boolean solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        /*for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }*/
        System.out.println(solutionPath.size());
        return solutionPath.size() != 0;
    }
}