import java.io.*;
import java.net.*;
import jade.core.Agent;

public class CircuitComponentAgent extends Agent {

    private Socket socket;
    private DataOutputStream output;

    @Override
    protected void setup() {
        try {
            socket = new Socket("localhost", 12345); // Connect to Processing on port 12345
            output = new DataOutputStream(socket.getOutputStream());

            // Send data (e.g., component position)
            output.writeUTF("Resistor at position (300, 300)");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
