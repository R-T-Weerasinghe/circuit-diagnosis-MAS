import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MASLauncher {
    public static void main(String[] args) {
        try {
            // Initialize JADE runtime and container
            Runtime runtime = Runtime.instance();
            Profile profile = new ProfileImpl();
            AgentContainer container = runtime.createMainContainer(profile);

            // Create and start the agents
            AgentController sensorAgent = container.createNewAgent("SensorAgent", "SensorAgent", new Object[] {});
            AgentController analysisAgent = container.createNewAgent("AnalysisAgent", "AnalysisAgent", new Object[] {});
            AgentController diagnosisAgent = container.createNewAgent("DiagnosisAgent", "DiagnosisAgent", new Object[] {});

            sensorAgent.start();
            analysisAgent.start();
            diagnosisAgent.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
