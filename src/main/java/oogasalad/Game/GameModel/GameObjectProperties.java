package oogasalad.Game.GameModel;

public interface GameObjectProperties {
  Map<String, String> properties;
  List<Tool> getCorrectTillingTools() {
    // parse
    properties.get("correctTillingTools");

  }
}
