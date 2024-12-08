// ResistorAgent.java
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.io.*;
import java.net.*;

// ResistorAgent.java
public class ResistorAgent extends Agent {
  private ComponentSpec spec;
  private Socket socket;
  private DataOutputStream output;
  
  @Override
  protected void setup() {
      spec = new ComponentSpec("R1", "resistor", 
          new Position(200, 300), 
          new String[]{"C1"});
          
      try {
          socket = new Socket("localhost", 12345);
          output = new DataOutputStream(socket.getOutputStream());
          
          // Send component info
          String message = String.format("%s,%s,%d,%d,%s",
              spec.id, spec.type, 
              spec.position.x, spec.position.y,
              String.join(";", spec.connections));
          output.writeUTF(message);
          
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}