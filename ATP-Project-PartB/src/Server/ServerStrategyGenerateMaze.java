package Server;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.nio.channels.Channels;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));

        try {
            ObjectInputStream in = new ObjectInputStream(interruptibleInputStream);
            ObjectOutputStream out = new ObjectOutputStream(outToClient);
            int[] indexes =(int[]) in.readObject();

            IMazeGenerator generator = Configurations.getInstance().getMazeGeneratingAlgorithm();
            Maze newMaze = generator.generate(indexes[0], indexes[1]);

//            byte[] array = newMaze.toByteArray();
//            MyCompressorOutputStream myCompressor = new MyCompressorOutputStream();
//            byte[] maze = myCompressor.write(array);
//            myCompressor.flush();

            //out.write(maze);
            out.flush();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
