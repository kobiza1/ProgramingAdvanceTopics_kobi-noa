package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.channels.Channels;


public class ServerStrategySolveSearchProblem implements IServerStrategy {

    /**
     * Overrides the server strategy method to solve a search problem based on a given maze.
     * @param inFromClient The InputStream for receiving data from the client.
     * @param outToClient The OutputStream for sending data to the client.
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        // Wrap the InputStream with an interruptible input stream to handle interruption during reading
        InputStream interruptibleInputStream = Channels.newInputStream(Channels.newChannel(inFromClient));

        try {
            ObjectInputStream in = new ObjectInputStream(interruptibleInputStream);
            ObjectOutputStream out = new ObjectOutputStream(outToClient);

            // Get the maze searching algorithm from configurations
            ISearchingAlgorithm searcher = Configurations.getInstance().getMazeSearchingAlgorithm();

            // Read the maze from the client input
            Maze maze = (Maze) in.readObject();

            Solution solution;
            int index = 0;
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            File solutionFile_maze = new File(tempDirectoryPath);

            // Find all files matching the "Maze" prefix in the temporary directory
            File[] all_files_match_maze = solutionFile_maze.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith("Maze");
                }
            });

            // Find all files matching the "Solution" prefix in the temporary directory
            File[] all_files_match_solution = solutionFile_maze.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith("Solution");
                }
            });

            // Check if a solution already exists for the received maze
            Solution sol = find_if_sol_exist(all_files_match_maze, all_files_match_solution, maze);
            if(sol != null){
                out.writeObject(sol);
            }
            else{
                // Compute a new solution for the maze
                index = find_max_value_maze(all_files_match_maze) + 1;

                solution = searcher.solve(searchableMaze);
                save_maze(maze, index, tempDirectoryPath);
                save_solution(index, solution, tempDirectoryPath);
                out.writeObject(solution);

            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds the maximum value of the maze file names in the given array of files.
     * @param allFilesMatchMaze An array of files matching the "Maze" prefix.
     * @return The maximum value of the maze file names.
     */
    private int find_max_value_maze(File[] allFilesMatchMaze) {
        String num_as_string, name_of_file;
        String[] parts;
        int num, max = -1;
        for(File file : allFilesMatchMaze){
             name_of_file = file.getName();

             parts = name_of_file.split("#");
             if(parts.length < 2){
                 continue;
             }
             num_as_string = parts[1];
             num = Integer.parseInt(num_as_string);
             if(num > max){
                 max = num;
             }
        }
        return max;
    }

    /**
     * Finds if a maze and its corresponding solution exist in the given arrays of maze and solution files.
     * @param all_files_match_maze An array of files matching the "Maze" prefix.
     * @param all_files_match_solution An array of files matching the "Solution" prefix.
     * @param maze_we_got The maze object received from the client.
     * @return The solution object if a matching maze and solution are found, otherwise null.
     */
    private Solution find_if_sol_exist(File[] all_files_match_maze, File[] all_files_match_solution, Maze maze_we_got){
        String name_to_search;
        String num_as_string = find_right_maze(maze_we_got, all_files_match_maze);
        try {
            if(num_as_string != null){
                for (File f : all_files_match_solution) {
                    name_to_search = "Solution #" + num_as_string;
                    if (f.getName().equals(name_to_search)) {
                        FileInputStream fileInput_sol = new FileInputStream(f.getPath());
                        ObjectInputStream objectInput_sol = new ObjectInputStream(fileInput_sol);
                        Solution sol = (Solution) objectInput_sol.readObject();
                        sol.setName(f.getName());
                        return sol;
                    }
                }
            }
            return null;
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String find_right_maze(Maze mazeWeGot, File[] allFilesMatchMaze) {
        String num_as_string, name_of_file;
        String[] parts;
        try {
            for (File file : allFilesMatchMaze) {
                FileInputStream fileInput = new FileInputStream(file.getPath());
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                name_of_file = file.getName();
                parts = name_of_file.split("#");
                if(parts.length < 2){
                    continue;
                }
                num_as_string = parts[1];
                Maze m = (Maze) objectInput.readObject();
                if(m.equals(mazeWeGot)){
                    return num_as_string;
                }
            }
            return null;
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void save_maze(Maze maze, int index, String directoryPath){

    try{

        File solutionFile_solution = new File(directoryPath);
        String tempFileName_maze = "Maze #" + index;
        File newFile_maze = new File(solutionFile_solution, tempFileName_maze);

        FileOutputStream fileOutput_maze = new FileOutputStream(newFile_maze.getPath());
        ObjectOutputStream objectOutput_maze = new ObjectOutputStream(fileOutput_maze);
        objectOutput_maze.writeObject(maze);
    }
    catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
    public void save_solution(int index, Solution solution, String directoryPath){

        try{
            File solutionFile_solution = new File(directoryPath);
            String tempFileName_sol = "Solution #" + index;
            File newFile_sol = new File(solutionFile_solution, tempFileName_sol);

            // Store the maze and solution objects in separate files
            FileOutputStream fileOutput_sol = new FileOutputStream(newFile_sol.getPath());
            ObjectOutputStream objectOutput_sol = new ObjectOutputStream(fileOutput_sol);
            objectOutput_sol.writeObject(solution);
            solution.setName(tempFileName_sol);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


