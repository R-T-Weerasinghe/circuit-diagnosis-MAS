// ResistorAgent.java
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.io.*;
import java.net.*;

public class ResistorAgent extends Agent {
    private ComponentSpec spec;
    private Socket socket;
    private DataOutputStream output;
    private float voltage;
    private float current;
    private float resistance;
    
    @Override
    protected void setup() {
        try {
            socket = new Socket("localhost", 12345);
            output = new DataOutputStream(socket.getOutputStream());
            
            // Send initial position
            output.writeUTF("R1,300,300");
            
            addBehaviour(new CyclicBehaviour(this) {
                public void action() {
                    // Handle messages from other agents
                }
            });
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected float calculateVoltage() {
        return current * resistance;
    }
    
    protected float calculateCurrent() {
        return voltage / resistance;
    }
}