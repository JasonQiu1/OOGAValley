package oogasalad.model.gameobject;

import java.util.Map;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.exception.BadValueParseException;
import oogasalad.model.api.exception.KeyNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
   * @param id           The id of the GameObject.
   * @param creationTime The game time at which this object was created
   */
  public Structure(String id, ReadOnlyGameTime creationTime) {
    super(id, creationTime);
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
  public Map<String, Integer> getItemsOnDestruction() {
    try {
      return getProperties().getStringIntegerMap(
          "dropsOnDestruction"); // e.g. {"item":"2", "otherItem":"1"}
    } catch (KeyNotFoundException | BadValueParseException e) {
      LOG.error("Couldn't find valid 'dropsOnDestruction' for '" + getId() + "'");
      throw new RuntimeException(e);
    }
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
    try {
      return getProperties().getBoolean("destructable");
    } catch (KeyNotFoundException | BadValueParseException e) {
      LOG.error("Couldn't find valid 'destructable' for '" + getId() + "'");
      throw new RuntimeException(e);
    }
  }

  private static final Logger LOG = LogManager.getLogger(Structure.class);
}
