package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
            int index = 0;
            SearchableMaze searchableMaze = new SearchableMaze(maze);



            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            File solutionFile_maze = new File(tempDirectoryPath);

            File[] all_files_match_maze = solutionFile_maze.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith("Maze");
                }
            });
            File[] all_files_match_solution = solutionFile_maze.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith("Solution");
                }
            });
            Solution sol = find_if_maze_and_sol_exist(all_files_match_maze, all_files_match_solution, maze);
            if(sol != null){
                out.writeObject(sol);
            }
            else{
                index = find_max_value_maze(all_files_match_maze) + 1;
                solution = searcher.solve(searchableMaze);

                File solutionFile_solution = new File(tempDirectoryPath);
                String tempFileName_maze = "Maze #" + index;
                String tempFileName_sol = "Solution #" + index;
                File newFile_maze = new File(solutionFile_solution, tempFileName_maze);
                File newFile_sol = new File(solutionFile_solution, tempFileName_sol);


                FileOutputStream fileOutput_sol = new FileOutputStream(newFile_sol.getPath());
                ObjectOutputStream objectOutput_sol = new ObjectOutputStream(fileOutput_sol);
                objectOutput_sol.writeObject(solution);
                FileOutputStream fileOutput_maze = new FileOutputStream(newFile_maze.getPath());
                ObjectOutputStream objectOutput_maze = new ObjectOutputStream(fileOutput_maze);
                objectOutput_maze.writeObject(maze);
                solutionToMaze.put(maze, solutionFile_solution);

                solution.setName(tempFileName_sol);
                out.writeObject(solution);

            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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

    private Solution find_if_maze_and_sol_exist(File[] all_files_match_maze, File[] all_files_match_solution, Maze maze_we_got){
        String num_as_string, name_of_file, name_to_search;
        String[] parts;
        try {
            for (File file : all_files_match_maze) {
                FileInputStream fileInput = new FileInputStream(file.getPath());
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                name_of_file = file.getName();
                parts = name_of_file.split("#");
                if(parts.length < 2){
                    continue;
                }
                num_as_string = parts[1];
                Maze m = (Maze) objectInput.readObject();
                if (m.equals(maze_we_got)) {
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
            }
            return null;
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


