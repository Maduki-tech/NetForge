package de.schlueter;

import de.schlueter.network.Server;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Server server = new Server(8080);
        server.listen();
    }
}
