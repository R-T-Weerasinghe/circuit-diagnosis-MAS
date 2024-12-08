import java.util.ArrayList;
import java.util.List;

public class ComponentSpec {
  public String id;
  public String type;
  public float value;
  public String unit;
  public Position position;
  public List<String> connections;

  public ComponentSpec(String id, String type, float value, String unit, Position position) {
      this.id = id;
      this.type = type;
      this.value = value;
      this.unit = unit;
      this.position = position;
      this.connections = new ArrayList<>();
  }
}