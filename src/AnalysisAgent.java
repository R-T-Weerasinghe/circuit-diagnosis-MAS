import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AnalysisAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                // Receive the sensor data
                ACLMessage msg = receive();
                if (msg != null) {
                    double sensorData = Double.parseDouble(msg.getContent());
                    System.out.println("AnalysisAgent: Received Sensor Data = " + sensorData);

                    // Analyze the data and decide if there is a fault
                    if (sensorData > 5.0) { // Example condition for faulty component
                        System.out.println("AnalysisAgent: Potential fault detected!");
                    } else {
                        System.out.println("AnalysisAgent: Circuit functioning normally.");
                    }
                } else {
                    block();
                }
            }
        });
    }
}
