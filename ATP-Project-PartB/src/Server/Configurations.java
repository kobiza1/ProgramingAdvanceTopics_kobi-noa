package Server;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class Configurations {
    private static Configurations instance;
    private int threadPoolSize;
    private String mazeGeneratingAlgorithm;
    private String mazeSearchingAlgorithm;

    /**
     * Initializes the Configurations class by loading configuration properties from a file.
     * The configuration properties include the thread pool size, maze generating algorithm, and maze searching algorithm.
     * These properties are read from a "config.properties" file located in the classpath.
     */
    private Configurations(){
        try {
            // Load the configuration properties file
            InputStream file = Configurations.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();
            prop.load(file);

            // Read and set the thread pool size
            threadPoolSize = Integer.parseInt(prop.getProperty("threadPoolSize"));
            // Read and set the maze generating algorithm
            mazeGeneratingAlgorithm = prop.getProperty("mazeGeneratingAlgorithm");
            // Read and set the maze searching algorithm
            mazeSearchingAlgorithm = prop.getProperty("mazeSearchingAlgorithm");

        } catch (IOException e){
            // Handle any IOException that occurs during loading of configuration properties
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the instance of the Configurations class.
     * If no instance exists, a new instance is created and assigned to the static variable 'instance'.
     * Subsequent calls to this method will return the same instance.
     *
     * @return The instance of the Configurations class.
     */
    public static Configurations getInstance(){
        if(instance==null)
            instance = new Configurations();
        return instance;
    }

    /**
     * Retrieves the thread pool size from the Configurations instance.
     *
     * @return The thread pool size configured in the Configurations instance.
     */
    public int getThreadPoolSize(){
        return threadPoolSize;
    }

    /**
     * Retrieves the instance of an IMazeGenerator based on the configured maze generating algorithm.
     * If the maze generating algorithm is set to "SimpleMazeGenerator", a new instance of SimpleMazeGenerator is returned.
     * If the maze generating algorithm is set to "MyMazeGenerator", a new instance of MyMazeGenerator is returned.
     * If the maze generating algorithm is not recognized or specified, a new instance of EmptyMazeGenerator is returned.
     *
     * @return An instance of IMazeGenerator based on the configured maze generating algorithm.
     */
    public IMazeGenerator getMazeGeneratingAlgorithm(){
        if (Objects.equals(mazeGeneratingAlgorithm, "SimpleMazeGenerator"))
            return new SimpleMazeGenerator();
        else if (Objects.equals(mazeGeneratingAlgorithm, "MyMazeGenerator"))
            return new MyMazeGenerator();
        else
            return new EmptyMazeGenerator();
    }

    /**
     * Retrieves the instance of an ISearchingAlgorithm based on the configured maze searching algorithm.
     * If the maze searching algorithm is set to "BreadthFirstSearch", a new instance of BreadthFirstSearch is returned.
     * If the maze searching algorithm is set to "DepthFirstSearch", a new instance of DepthFirstSearch is returned.
     * If the maze searching algorithm is not recognized or specified, a new instance of BestFirstSearch is returned.
     *
     * @return An instance of ISearchingAlgorithm based on the configured maze searching algorithm.
     */
    public ISearchingAlgorithm getMazeSearchingAlgorithm(){
        if (Objects.equals(mazeSearchingAlgorithm, "BreadthFirstSearch"))
            return new BreadthFirstSearch();
        else if (Objects.equals(mazeSearchingAlgorithm, "DepthFirstSearch"))
            return new DepthFirstSearch();
        else
            return new BestFirstSearch();
    }
}
