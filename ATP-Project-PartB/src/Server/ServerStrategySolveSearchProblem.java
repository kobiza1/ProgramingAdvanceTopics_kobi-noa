package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.channels.Channels;


public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));

        try {
            ObjectInputStream in = new ObjectInputStream(interruptibleInputStream);
            ObjectOutputStream out = new ObjectOutputStream(outToClient);
            ISearchingAlgorithm searcher = Configurations.getInstance().getMazeSearchingAlgorithm();

            Maze maze = (Maze) in.readObject();

            SearchableMaze searchableMaze = new SearchableMaze(maze);
            Solution solution = searcher.solve(searchableMaze);

            out.writeObject(solution);
            out.flush();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
