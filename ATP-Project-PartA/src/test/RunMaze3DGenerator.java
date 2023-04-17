package test;

import algorithms.maze3D.*;


public class RunMaze3DGenerator {
    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator3D mazeGenerator3D) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator3D.measureAlgorithmTimeMillis(10/*depth*/,10/*rows*/, 10/*columns*/)));
        // generate another maze
        Maze3D maze3D = mazeGenerator3D.generate(5/*depth*/,5/*rows*/, 5/*columns*/);

        // prints the maze
        maze3D.print();

        // get the maze entrance
        Position3D startPosition = maze3D.getStartPosition();

        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{depth,row,column}"

        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze3D.getGoalPosition()));

    }
}
