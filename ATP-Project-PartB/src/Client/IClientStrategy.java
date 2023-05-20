package Client;

import java.io.InputStream;
import java.io.OutputStream;

public interface IClientStrategy {
    /**
     * Defines the strategy to be executed by the client when communicating with the server.
     * This method receives an InputStream for receiving data from the server and an OutputStream for sending data to the server.
     * Implementing classes should define the specific behavior for interacting with the server.
     *
     * @param inFromServer The InputStream for receiving data from the server.
     * @param outToServer The OutputStream for sending data to the server.
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
