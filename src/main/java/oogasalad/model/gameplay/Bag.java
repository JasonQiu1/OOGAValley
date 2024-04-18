package oogasalad.model.gameplay;

import java.util.List;
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
  public List<ReadOnlyItem> getItems() {
    // TODO: IMPLEMENT
    return null;
  }
}
