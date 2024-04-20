package oogasalad.model.gameplay;

import java.util.Map;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;

/**
 * The player's bag that contains items.
 *
 * @author Jason Qiu
 */
public class Bag implements ReadOnlyBag {

  /**
   * Returns the items currently held in the bag.
   *
   * @return the items currently held in the bag.
   */
  @Override
  public Map<ReadOnlyItem, Integer> getItems() {
    // TODO: IMPLEMENT
    return null;
  }
}
