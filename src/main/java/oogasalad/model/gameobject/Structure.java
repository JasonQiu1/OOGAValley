package oogasalad.model.gameobject;

import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.gameplay.GameTime;

/**
 * Represents a structure within the game world, extending the general functionality of a
 * {@link GameObject}. Structures have specific properties and behaviors, such as being
 * destructible, potentially harvestable, and having a particular state that leads to expiration.
 * This class encapsulates the behavior specific to structures, including interactions that may
 * result in the creation of collectable items upon destruction.
 *
 * @author Spencer Katz, Jason Qiu
 */
public class Structure extends GameObject implements StructureObject {

  /**
   * Constructs a new Structure with the given ID, initial state, and specific properties.
   *
   * @param properties   The properties specific to this structure, defining its behavior upon
   *                     interaction, conditions for harvesting, and what happens upon its
   *                     destruction.
   * @param creationTime The game time at which this object was created
   */
  public Structure(ReadOnlyProperties properties, GameTime creationTime) {
    super(properties, creationTime);
  }

  /**
   * Gets the ID of the collectable item that this structure produces upon destruction. This method
   * is relevant for structures that, when destroyed or interacted with in a certain way, result in
   * the creation of a new collectable item.
   *
   * @return A String representing the ID of the collectable item produced upon the structure's
   * destruction.
   */
  @Override
  public String getItemsOnDestruction() {
    // TODO: EXTEND TO RETURN MULTIPLE DROPS OF DIFFERENT QUANTITIES
    // return properties.getStringMap("destructionDrops"); // e.g. {"item":"2", "otherItem":"1"}
    return getProperties().getString("dropsOnDestruction");
  }

  /**
   * Checks if the structure is harvestable. Structures can be considered harvestable based on their
   * current state and specific properties, which might enable players to collect resources or items
   * from them.
   *
   * @return {@code true} if the structure is harvestable in its current state, {@code false}
   * otherwise.
   */
  @Override
  public boolean isHarvestable() {
    return getProperties().getBoolean("destructable");
  }
}
