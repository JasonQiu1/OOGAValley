package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.GameObjectProperties;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.LandProperties;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.StructureProperties;
import oogasalad.Game.GameModel.exception.IncorrectPropertyFileType;

/**
 * Represents a piece of land within the game world, extending the {@link GameObject} class.
 * This class encapsulates land-specific behaviors and properties, including the ability to be planted upon.
 * The {@link Plantable} interface implementation indicates that certain objects can be planted on this land,
 * depending on its properties.
 */
public class Land extends GameObject implements Plantable {

  private LandProperties properties;

  /**
   * Constructs a new piece of Land with the specified ID, initial state, and land-specific properties.
   * These properties determine the land's interactions within the game, such as its plantability.
   *
   * @param id The unique identifier for this piece of land.
   * @param properties The properties defining the behavior and characteristics of this land.
   * @param creationTime The game time at which this object was created
   */
  public Land(String id, LandProperties properties, GameTime creationTime) {
    super(id, properties, creationTime);
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

  /**
   * Overrides the setProperties method to update the structure's specific properties.
   * Ensures the new properties are correctly cast to StructureProperties.
   *
   * @param properties The new properties to set for the structure.
   */
  @Override
  public void setProperties(GameObjectProperties properties) {
    super.setProperties(properties);
    try {
      this.properties = (LandProperties) properties;
    } catch (ClassCastException e) {
      throw new IncorrectPropertyFileType("Provided properties cannot be cast to correct properties type");
    }
  }
}
