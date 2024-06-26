package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final int listeningIntervalMS;
    private final IServerStrategy strategy;
    private volatile boolean stop;
    private final ExecutorService threadPool; // Thread pool
    private final Thread serverThread;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        // initialize a new fixed thread pool with threadPoolSize threads from Configuration file
        Configurations conf = Configurations.getInstance();
        int threadPoolSize = conf.getThreadPoolSize();
        this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
        this.serverThread = new Thread(this::startServer);
    }

    /**
     * Starts the server by starting the server thread.
     */
    public void start(){
        serverThread.start();
    }

    /**
     * Starts the server and listens for client connections.
     * The server runs in a loop until the stop flag is set.
     * For each client connection, a new thread from the thread pool is used to execute the server strategy.
     */
    public void startServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());
                    threadPool.execute(() -> ServerStrategy(clientSocket));

                } catch (SocketTimeoutException ignore) {

                }
            }

            serverSocket.close();
            threadPool.shutdownNow();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Stops the server by setting the stop flag.
     */
    public void stop(){
        synchronized ((Object) stop) {
            stop = true;
        }
    }

    /**
     * Executes the server strategy for handling client requests.
     * The server strategy is responsible for processing client input and providing output.
     *
     * @param clientSocket The socket representing the client connection.
     */
    private void ServerStrategy(Socket clientSocket){
        try {
            strategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
