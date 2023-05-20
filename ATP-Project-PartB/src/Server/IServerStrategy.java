package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {
    /**
     * Defines the server strategy for handling client requests.
     * Implementing classes must provide their own implementation of this method.
     *
     * @param var1 The InputStream for receiving data from the client.
     * @param var2 The OutputStream for sending data to the client.
     */
    void serverStrategy(InputStream var1, OutputStream var2);
}
