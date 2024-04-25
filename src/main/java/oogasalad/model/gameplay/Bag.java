package oogasalad.model.gameplay;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.gameobject.Item;

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

  private final Map<ReadOnlyItem, Integer> items;

  @Override
  public Map<ReadOnlyItem, Integer> getItems() {
    return Collections.unmodifiableMap(items);
  }

  /**
   * Adds a certain amount of a single item to the bag.
   *
   * @param id     the id of the item to add.
   * @param amount the amount of the item to add.
   */
  public void addItem(String id, int amount) {
    ReadOnlyItem item = new Item(id);
    items.put(item, items.getOrDefault(item, 0) + amount);
  }

  /**
   * Adds multiple items of particular amounts to the bag.
   *
   * @param itemsToAdd a map of items and amounts of them to add.
   */
  public void addItems(Map<String, Integer> itemsToAdd) {
    for (Map.Entry<String, Integer> entry : itemsToAdd.entrySet()) {
      addItem(entry.getKey(), entry.getValue());
    }
  }

  /**
   * Removes an amount of an item from the bag.
   *
   * @param id the id of the item to remove.
   * @param amountToRemove the amount of the item to remove.
   */
  public void removeItem(String id, int amountToRemove) {
    items.computeIfPresent(new Item(id), (ReadOnlyItem item, Integer item_amount) -> {
      if (amountToRemove > item_amount) {
        return null;
      }
      return item_amount - amountToRemove;
    });
  }

  /**
   * Removes all of the given item from the bag.
   *
   * @param id the item to completely remove.
   */
  public void removeAllOfItem(String id) {
    items.remove(new Item(id));
  }
}
