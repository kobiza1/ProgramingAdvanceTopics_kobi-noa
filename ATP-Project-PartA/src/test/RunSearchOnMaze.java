package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;
import java.util.ArrayList;


public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        solveProblem(searchableMaze, new BreadthFirstSearch());
        //solveProblem(searchableMaze, new DepthFirstSearch());
        //solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        long start_time = System.currentTimeMillis();
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        long end_time = System.currentTimeMillis();
        System.out.println(end_time-start_time);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
//        IMazeGenerator mg = new MyMazeGenerator();
//        long maze = mg.measureAlgorithmTimeMillis(1000, 1000);
//        Maze m1 = mg.generate(10, 10);
//        //SimpleMazeGenerator simple_maze = new SimpleMazeGenerator();
//        //long i = simple_maze.measureAlgorithmTimeMillis(1000, 1000);
//        System.out.println(maze);
//        m1.Print();

}
