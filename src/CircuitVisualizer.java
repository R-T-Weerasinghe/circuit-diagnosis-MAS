// CircuitVisualizer.java
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

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

class CircuitPanel extends JPanel {
    private java.util.List<String> components = new java.util.ArrayList<>();

    public void updateComponent(String component) {
        components.add(component);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (String comp : components) {
            String[] parts = comp.split(",");
            if (parts.length >= 3) {
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                g.drawRect(x-20, y-10, 40, 20);
                g.drawString(parts[0], x-10, y+5);
            }
        }
    }
}