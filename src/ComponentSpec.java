import java.util.ArrayList;
import java.util.List;

public class ComponentSpec {
  String id;
  String type;
  Position position;
  String[] connections;

  public ComponentSpec(String id, String type, Position position, String[] connections) {
    this.id = id;
    this.type = type;
    this.position = position;
    this.connections = connections;
  }
}