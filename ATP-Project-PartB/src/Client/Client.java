package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private final InetAddress serverIP;
    private final int serverPort;
    private final IClientStrategy strategy;

    /**
     * Constructs a new Client object with the specified server IP address, port number, and client strategy.
     * @param serverIP The IP address of the server to connect to.
     * @param serverPort The port number of the server to connect to.
     * @param strategy The client strategy to execute for interacting with the server.
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    /**
     * Establishes a connection with the server and executes the client strategy.
     */
    public void communicateWithServer(){
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


