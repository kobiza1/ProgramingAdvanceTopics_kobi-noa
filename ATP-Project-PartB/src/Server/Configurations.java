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

    private Configurations(){
        try {
            //String path = "resources/config.properties";
            //InputStream file = new FileInputStream(path);
            InputStream file = Configurations.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();
            prop.load(file);

            threadPoolSize = Integer.parseInt(prop.getProperty("threadPoolSize"));
            mazeGeneratingAlgorithm = prop.getProperty("mazeGeneratingAlgorithm");
            mazeSearchingAlgorithm = prop.getProperty("mazeSearchingAlgorithm");

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Configurations getInstance(){
        if(instance==null)
            instance = new Configurations();
        return instance;
    }

    public int getThreadPoolSize(){
        return threadPoolSize;
    }

    public IMazeGenerator getMazeGeneratingAlgorithm(){
        if (Objects.equals(mazeGeneratingAlgorithm, "SimpleMazeGenerator"))
            return new SimpleMazeGenerator();
        else if (Objects.equals(mazeGeneratingAlgorithm, "MyMazeGenerator"))
            return new MyMazeGenerator();
        else
            return new EmptyMazeGenerator();
    }

    public ISearchingAlgorithm getMazeSearchingAlgorithm(){
        if (Objects.equals(mazeSearchingAlgorithm, "BreadthFirstSearch"))
            return new BreadthFirstSearch();
        else if (Objects.equals(mazeSearchingAlgorithm, "DepthFirstSearch"))
            return new DepthFirstSearch();
        else
            return new BestFirstSearch();
    }
}
