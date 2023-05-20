package Server;

import IO.MyCompressorOutputStream;
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

            //OutputStream compressor = new MyCompressorOutputStream(out);
            String tempFileName = "tempFile.txt";

            OutputStream compressor = new MyCompressorOutputStream(new FileOutputStream(tempFileName));
            byte[] array = newMaze.toByteArray();
            compressor.write(array);
            compressor.flush();

            InputStream fileInput = new FileInputStream(tempFileName);
            byte[] data = fileInput.readAllBytes();
            File tempFile = new File(tempFileName);
            tempFile.delete();

            out.writeObject(data);
            out.flush();
            out.close();
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
