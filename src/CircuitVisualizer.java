// CircuitVisualizer.java
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class CircuitVisualizer extends JFrame {
    private CircuitPanel panel;
    private ServerSocket serverSocket;

    public CircuitVisualizer() {
        setTitle("Circuit Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new CircuitPanel();
        add(panel);
        
        startServer();
    }

    private void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(12345);
                System.out.println("Visualizer waiting for connections on port 12345...");
                while (true) {
                    Socket socket = serverSocket.accept();
                    handleClientConnection(socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleClientConnection(Socket socket) {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            String message = input.readUTF();
            panel.updateComponent(message);
            panel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CircuitVisualizer().setVisible(true);
        });
    }
}

// class CircuitPanel extends JPanel {
//     private java.util.List<String> components = new java.util.ArrayList<>();

//     public void updateComponent(String component) {
//         components.add(component);
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         for (String comp : components) {
//             String[] parts = comp.split(",");
//             if (parts.length >= 3) {
//                 int x = Integer.parseInt(parts[1]);
//                 int y = Integer.parseInt(parts[2]);
//                 Graphics2D g2 = (Graphics2D) g;
//                 g2.setStroke(new BasicStroke(3)); // Set the line width to 3
//                 g2.drawRect(x-20, y-10, 40, 20);
//                 g.setFont(new Font("default", Font.BOLD, 18));
//                 g.drawString(parts[0], x-10, y+7);
//             }
//         }
//     }
// }

// CircuitVisualizer.java
class CircuitPanel extends JPanel {
  private Map<String, ComponentInfo> components = new HashMap<>();
  
  static class ComponentInfo {
      String id;
      String type;
      int x, y;
      String[] connections;
  }
  
  public void updateComponent(String data) {
      String[] parts = data.split(",");
      ComponentInfo info = new ComponentInfo();
      info.id = parts[0];
      info.type = parts[1];
      info.x = Integer.parseInt(parts[2]);
      info.y = Integer.parseInt(parts[3]);
      info.connections = parts[4].split(";");
      components.put(info.id, info);
      repaint();
  }
  
  @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setStroke(new BasicStroke(2));
      
      // Draw components
      for (ComponentInfo comp : components.values()) {
          if (comp.type.equals("resistor")) {
              drawResistor(g2, comp.x, comp.y, comp.id);
          } else if (comp.type.equals("capacitor")) {
              drawCapacitor(g2, comp.x, comp.y, comp.id);
          }
      }
      
      // Draw connections
      g2.setColor(Color.BLUE);
      for (ComponentInfo comp : components.values()) {
          for (String connId : comp.connections) {
              ComponentInfo conn = components.get(connId);
              if (conn != null) {
                  g2.drawLine(comp.x, comp.y, conn.x, conn.y);
              }
          }
      }
  }
  
  private void drawResistor(Graphics2D g, int x, int y, String id) {
      g.setColor(Color.BLACK);
      g.drawRect(x-20, y-10, 40, 20);
      g.drawString(id, x-10, y+5);
  }
  
  private void drawCapacitor(Graphics2D g, int x, int y, String id) {
      g.setColor(Color.BLACK);
      g.drawLine(x-10, y-15, x-10, y+15);
      g.drawLine(x+10, y-15, x+10, y+15);
      g.drawString(id, x-10, y+25);
  }
}