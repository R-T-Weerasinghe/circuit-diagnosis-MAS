import java.io.*;
import java.net.*;

public class CircuitVisualization extends PApplet {

    private Socket socket;
    private DataInputStream input;

    public static void main(String[] args) {
        PApplet.main("CircuitVisualization");
    }

    @Override
    public void settings() {
        size(600, 600); // Set the canvas size
    }

    @Override
    public void setup() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            println("Server started on port 12345..."); // Confirm that the server is ready
            socket = serverSocket.accept(); // Wait for the client (JADE) to connect
            input = new DataInputStream(socket.getInputStream());
            println("Client connected!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        try {
            String message = input.readUTF();
            background(255);
            fill(0);
            ellipse(300, 300, 50, 50); // Draw circle (component)
            text(message, 250, 250); // Display received message
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
