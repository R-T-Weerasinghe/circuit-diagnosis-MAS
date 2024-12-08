// CapacitorAgent.java

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import jade.core.Agent;

public class CapacitorAgent extends Agent {
  private ComponentSpec spec;
  private Socket socket;
  private DataOutputStream output;
  
  @Override
  protected void setup() {
      spec = new ComponentSpec("C1", "capacitor", 
          new Position(400, 300), 
          new String[]{"R1", "GND"});
          
      try {
          socket = new Socket("localhost", 12345);
          output = new DataOutputStream(socket.getOutputStream());
          
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