import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Main {
    public static void main(String[] args) {
        // Initialize JADE runtime
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1099");  // Default port
        AgentContainer container = runtime.createMainContainer(profile);

        try {
            // Create a ResistorAgent
            AgentController agentController = container.createNewAgent("ResistorAgent", "CircuitComponentAgent", new Object[] {});
            agentController.start();

            // Create a CapacitorAgent
            agentController = container.createNewAgent("CapacitorAgent", "CircuitComponentAgent", new Object[] {});
            agentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
