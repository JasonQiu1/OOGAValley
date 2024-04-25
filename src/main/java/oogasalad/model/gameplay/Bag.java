package oogasalad.model.gameplay;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameobject.ItemsToAdd;

/**
 * The player's bag that contains items. Provides methods to add/remove items from the bag.
 *
 * @author Jason Qiu
 */
public class Bag implements ReadOnlyBag {

  public Bag() {
    this.items = new HashMap<>();
  }

  /**
   * Returns the items currently held in the bag.
   *
   * @return the items currently held in the bag.
   */
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
   * @param itemsToAdd a list of items and amounts of them to add.
   */
  public void addItems(List<ItemsToAdd> itemsToAdd) {
    for (ItemsToAdd items : itemsToAdd) {
      addItem(items.id(), items.quantity());
    }
  }

  /**
   * Removes an amount of an item from the bag.
   *
   * @param id             the id of the item to remove.
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

  private final Map<ReadOnlyItem, Integer> items;
}
