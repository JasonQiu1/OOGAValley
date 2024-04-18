package oogasalad.model.api;

import java.util.List;

/**
 * Provides an interface to get information about the player's bag and items within.
 *
 * @author Jason Qiu
 */
public interface ReadOnlyBag {

  /**
   * Returns the items currently held in the bag.
   *
   * @return the items currently held in the bag.
   */
  List<ReadOnlyItem> getItems();
}
