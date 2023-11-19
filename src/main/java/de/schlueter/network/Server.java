package de.schlueter.network;

import de.schlueter.protocols.HTTP;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 */
public class Server implements ServerInterface {
    private int port;

    ServerSocket serverSocket;
    Socket socket;

    public Server(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        } catch (Exception e) {
            System.out.println("Error while creating ServerSocket");
            System.exit(1);
        }
    }

    @Override
    public void listen() {
        try {
            socket = serverSocket.accept();
            System.out.println("Client connected");
            handleClient();
        } catch (Exception e) {
            System.out.println("Error while accepting connection");
            System.exit(1);
        }
    }

    @Override
    public void handleClient() {
        try {
            // Read the Request
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder requestBuilder = new StringBuilder();

            String line;
            while (!(line = in.readLine()).isEmpty()) {
                requestBuilder.append(line + "\r\n");
            }
            String request = requestBuilder.toString();
            String[] requestLines = request.split("\r\n");
            String[] requestLine = requestLines[0].split(" ");

            String httpMethode = requestLine[0];
            String httpPath = requestLine[1];

            send(handleRequest(httpMethode, httpPath));
        } catch (Exception e) {
            System.out.println("Error while handling client");
            System.exit(1);
        }
    }

    private String handleRequest(String methode, String path) {
        String response = "";
        HTTP http = new HTTP();

        switch (methode) {
            case "GET":
                response = http.handleGet(path);

                break;
            case "POST":
                response = http.handlePost(path);
                break;

            default:
                break;
        }

        return response;
    }

    @Override
    public void send(String message) {
        try {
            socket.getOutputStream().write(message.getBytes());
        } catch (Exception e) {
            System.out.println("Error while sending message");
            System.exit(1);
        }
    }

    @Override
    public void close() {
        try {
            send("{\"type\": \"DISCONNECTED\"}");
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error while closing socket");
            System.exit(1);
        }
    }
}
