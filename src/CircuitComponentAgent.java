import jade.core.Agent;
// import jade.core.behaviours.CyclicBehaviour;
import java.io.*;
import java.net.*;

public class CircuitComponentAgent extends Agent {

    // This is where we will define properties for the component
    // private String componentType;
    private Socket socket;
    private DataOutputStream output;

    @Override
    protected void setup() {
        try {
            // Set up a connection to the Processing visualization
            socket = new Socket("localhost", 12345); // Connect to Processing server
            output = new DataOutputStream(socket.getOutputStream());

            // Send data (e.g., component position)
            output.writeUTF("Resistor at position (300, 300)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // // Sample behavior for handling messages
    // private class ComponentBehaviour extends CyclicBehaviour {
    //     @Override
    //     public void action() {
    //         // Handle incoming messages here (for simplicity, just printing the message)
    //         System.out.println("Received message by " + getLocalName());
    //         block(); // Block until next message is received
    //     }
    // }
}
