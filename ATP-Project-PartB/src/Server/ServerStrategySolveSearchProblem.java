package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.channels.Channels;
import java.util.Hashtable;
import java.util.List;


public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private Hashtable<Integer, File> solutionToMaze;

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
            int hashMaze = maze.hashCode();
            Solution solution;
            if(!solutionToMaze.containsKey(hashMaze)) {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                solution = searcher.solve(searchableMaze);


                String tempDirectoryPath = System.getProperty("java.io.tmpdir");
                File solutionFile = new File(tempDirectoryPath);

                int index = maze.hashCode();
                String tempFileName = "Solution"+index+".txt";
                File newFile = new File(solutionFile, tempFileName);

                FileOutputStream fileOutput = new FileOutputStream(newFile.getPath());
                ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                objectOutput.writeObject(solution);
                solutionToMaze.put(index, solutionFile);
            } else {
                File solutionFile = solutionToMaze.get(hashMaze);
                FileInputStream fileInput = new FileInputStream(solutionFile.getPath());
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                solution = (Solution) objectInput.readObject();
            }

            out.writeObject(solution);
            out.flush();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println("Temporary directory: " + tmpDir);
    }
}


