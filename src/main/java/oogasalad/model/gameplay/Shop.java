package oogasalad.model.gameplay;

import java.util.Map;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.model.gameobject.Updatable;

/**
 * The shop contains items that swap out over time and go for different prices.
 *
 * @author Jason Qiu
 */
public class Shop implements ReadOnlyShop, Updatable {

  /**
   * Returns a map of items being sold, with their values being their prices.
   *
   * @return a map of items being sold, with their values being their prices.
   */
  @Override
  public Map<ReadOnlyItem, Double> getItems() {
    // TODO: IMPLEMENT
    return null;
  }

  /**
   * Updates the shop's items or prices over time.
   *
   * @param gameTime The current game time, providing context for time-based updates.
   */
  @Override
  public void update(ReadOnlyGameTime gameTime) {
// TODO: IMPLEMENT
  }
}
