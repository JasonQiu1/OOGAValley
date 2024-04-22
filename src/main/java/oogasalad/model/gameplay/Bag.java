package oogasalad.model.gameplay;

import java.util.HashMap;
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

  public Bag() {
    this.itemMap = new HashMap<>();
  }

  private Map<ReadOnlyItem, Integer> itemMap;

  @Override
  public Map<ReadOnlyItem, Integer> getItems() {
    return itemMap;

  }
}
