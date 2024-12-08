import processing.core.PApplet;

public class CircuitVisualization extends PApplet {

    public static void main(String[] args) {
        PApplet.main("CircuitVisualization");
    }

    @Override
    public void settings() {
        size(600, 600); // Set the canvas size
    }

    @Override
    public void setup() {
        background(255); // Set the background color to white
    }

    @Override
    public void draw() {
        // For now, just draw a simple circle to represent a component
        fill(0);
        ellipse(300, 300, 50, 50); // Circle at the center
    }
}
