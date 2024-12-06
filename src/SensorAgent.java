import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class SensorAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                // Simulate reading from the circuit (e.g., voltage or current)
                double sensorData = readSensorData();
                System.out.println("SensorAgent: Sensor Data = " + sensorData);

                // Send the sensor data to the AnalysisAgent
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(getAID("AnalysisAgent")); // Assuming there's an agent named AnalysisAgent
                msg.setContent(String.valueOf(sensorData));
                send(msg);
            }
        });
    }

    // Simulate sensor reading (in reality, use actual data collection from PCB)
    private double readSensorData() {
        return Math.random() * 10;  // Just random data for now
    }
}
