package de.schlueter.network;

/**
 * ServerInterface
 */
public interface ServerInterface {

    public void listen();

    void handleClient();

    void send(String message);

    void close();


}
