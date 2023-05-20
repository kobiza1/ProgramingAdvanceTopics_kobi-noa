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
        // Wrap the InputStream with an interruptible input stream to handle interruption during reading
        InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));

        try {
            ObjectInputStream in = new ObjectInputStream(interruptibleInputStream);
            ObjectOutputStream out = new ObjectOutputStream(outToClient);

            // Read the indexes array from the client input
            int[] indexes =(int[]) in.readObject();

            // Get the maze generating algorithm from configurations
            IMazeGenerator generator = Configurations.getInstance().getMazeGeneratingAlgorithm();

            // Generate a new maze based on the indexes
            Maze newMaze = generator.generate(indexes[0], indexes[1]);

            // Compress the maze data and store it in a temporary file
            String tempFileName = "tempFile.txt";
            OutputStream compressor = new MyCompressorOutputStream(new FileOutputStream(tempFileName));
            byte[] array = newMaze.toByteArray();
            compressor.write(array);
            compressor.flush();
            compressor.close();

            // Read the compressed maze data from the temporary file
            InputStream fileInput = new FileInputStream(tempFileName);
            byte[] data = fileInput.readAllBytes();

            // Delete the temporary file
            File tempFile = new File(tempFileName);
            tempFile.delete();

            // Send the compressed maze data back to the client
            out.writeObject(data);
            out.flush();
            out.close();
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
