import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class DiagnosisAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                // Receive analysis data (from one or more analysis agents)
                ACLMessage msg = receive();
                if (msg != null) {
                    // In a more complex scenario, you could collect data from multiple agents and perform a thorough diagnosis
                    System.out.println("DiagnosisAgent: Analysis result received: " + msg.getContent());
                    System.out.println("DiagnosisAgent: Final diagnosis: Faulty component detected.");
                } else {
                    block();
                }
            }
        });
    }
}
