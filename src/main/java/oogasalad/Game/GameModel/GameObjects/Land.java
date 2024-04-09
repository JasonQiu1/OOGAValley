package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.PropertiesOfGameObjects.LandProperties;

/**
 * Represents a piece of land within the game world, extending the {@link GameObject} class.
 * This class encapsulates land-specific behaviors and properties, including the ability to be planted upon.
 * The {@link Plantable} interface implementation indicates that certain objects can be planted on this land,
 * depending on its properties.
 */
public class Land extends GameObject implements Plantable {

  private final LandProperties properties;

  /**
   * Constructs a new piece of Land with the specified ID, initial state, and land-specific properties.
   * These properties determine the land's interactions within the game, such as its plantability.
   *
   * @param id The unique identifier for this piece of land.
   * @param startState The initial state of the land, affecting its appearance and interactions.
   * @param properties The properties defining the behavior and characteristics of this land.
   */
  public Land(String id, int startState, LandProperties properties) {
    super(id, startState, properties);
    this.properties = properties;
  }

  /**
   * Determines whether the land is currently plantable. The plantability of the land is defined by
   * its specific properties, which can influence game dynamics by allowing or disallowing the planting
   * of objects based on the current game state or conditions.
   *
   * @return {@code true} if the land is plantable; {@code false} otherwise.
   */
  @Override
  public boolean getIsPlantable() {
    return properties.getIsPlantable();
  }
}
