package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.PropertiesOfGameObjects.StructureProperties;

/**
 * Represents a structure within the game world, extending the general functionality of a {@link GameObject}.
 * Structures have specific properties and behaviors, such as being destructible, potentially harvestable,
 * and having a particular state that leads to expiration. This class encapsulates the behavior specific
 * to structures, including interactions that may result in the creation of collectable items upon destruction.
 */
public class Structure extends GameObject implements StructureObject {

  private StructureProperties properties;

  /**
   * Constructs a new Structure with the given ID, initial state, and specific properties.
   *
   * @param id The unique identifier for this structure.
   * @param properties The properties specific to this structure, defining its behavior upon interaction,
   *                   conditions for harvesting, and what happens upon its destruction.
   * @param creationTime The game time at which this object was created
   */
  public Structure(String id, StructureProperties properties, GameTime creationTime) {
    super(id, properties, creationTime);
    this.properties = properties;
  }

  /**
   * Gets the ID of the collectable item that this structure produces upon destruction.
   * This method is relevant for structures that, when destroyed or interacted with in a certain way,
   * result in the creation of a new collectable item.
   *
   * @return A String representing the ID of the collectable item produced upon the structure's destruction.
   */
  @Override
  public String getCollectableOnDestruction() {
    return properties.getCollectableOnDestruction();
  }

  /**
   * Checks if the structure is harvestable. Structures can be considered harvestable based on their
   * current state and specific properties, which might enable players to collect resources or items from them.
   *
   * @return {@code true} if the structure is harvestable in its current state, {@code false} otherwise.
   */
  @Override
  public boolean isHarvestable() {
    return properties.isHarvestable();
  }


}
