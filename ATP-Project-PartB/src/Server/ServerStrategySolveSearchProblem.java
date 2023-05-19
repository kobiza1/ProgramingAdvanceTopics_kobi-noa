package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.channels.Channels;
import java.util.Hashtable;
import java.util.List;


public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private Hashtable<Maze, File> solutionToMaze;

    public ServerStrategySolveSearchProblem(){
        solutionToMaze = new Hashtable<>();
    }
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));

        try {
            ObjectInputStream in = new ObjectInputStream(interruptibleInputStream);
            ObjectOutputStream out = new ObjectOutputStream(outToClient);
            ISearchingAlgorithm searcher = Configurations.getInstance().getMazeSearchingAlgorithm();

            Maze maze = (Maze) in.readObject();
            Solution solution;
            if(!solutionToMaze.containsKey(maze)) {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                solution = searcher.solve(searchableMaze);

                String tempDirectoryPath = System.getProperty("java.io.tmpdir");
                File solutionFile = new File(tempDirectoryPath);
                FileOutputStream fileOutput = new FileOutputStream(solutionFile);
                ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                objectOutput.writeObject(solution);
                solutionToMaze.put(maze, solutionFile);
            } else {
                File solutionFile = solutionToMaze.get(maze);
                FileInputStream fileInput = new FileInputStream(solutionFile);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                solution = (Solution) objectInput.readObject();
            }

            out.writeObject(solution);
            out.flush();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


