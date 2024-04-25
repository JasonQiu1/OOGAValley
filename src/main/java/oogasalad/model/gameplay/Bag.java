package oogasalad.model.gameplay;

import java.util.HashMap;
import java.util.Map;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;

/**
 * The player's bag that contains items. Provides methods to add/remove items from the bag.
 *
 * @author Jason Qiu
 */
public class Bag implements ReadOnlyBag {

  /**
   * Returns the items currently held in the bag.
   *
   * @return the items currently held in the bag.
   */

  public Bag() {
    this.items = new HashMap<>();
  }

  private Map<ReadOnlyItem, Integer> items;

  @Override
  public Map<ReadOnlyItem, Integer> getItems() {
    return items;
  }
}
