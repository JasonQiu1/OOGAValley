package oogasalad.model.gameplay;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

  // Amount of in-game minutes to pass before rotating the items in the shop.
  // TODO: EXTERNALIZE TO RULES PROPERTIES
  public static final int shopRotationTime = 24 * 60;

  /**
   * Initialize a new Shop with the given configuration.
   *
   * @param creationTime     the time the shop was initially created.
   * @param possibleItems    the possible items for sale.
   * @param itemRotationSize the number of items to put on sale.
   */
  public Shop(ReadOnlyGameTime creationTime, List<ReadOnlyItem> possibleItems,
      int itemRotationSize) {
    this.itemRotation = new HashMap<>();
    this.itemRotationSize = itemRotationSize;
    setPossibleItems(possibleItems);
    forceItemRotation(creationTime);
  }

  /**
   * Returns a map of items being sold, with their values being their prices.
   *
   * @return a map of items being sold, with their values being their prices.
   */
  @Override
  public Map<ReadOnlyItem, Double> getItems() {
    return itemRotation;
  }

  /**
   * Updates the shop's items or prices over time. Currently decides to update randomly.
   * TODO: Externalize this to the rules configuration
   *
   * @param gameTime The current game time, providing context for time-based updates.
   */
  @Override
  public void update(ReadOnlyGameTime gameTime) {
    if (previousRotationTime.getDifferenceInMinutes(gameTime) > shopRotationTime) {
      forceItemRotation(gameTime);
    }
  }

  /**
   * Get all possible items to rotate through.
   *
   * @return the unmodifiable list of possible items to rotate through.
   */
  public List<ReadOnlyItem> getCopyOfPossibleItems() {
    return Collections.unmodifiableList(possibleItems);
  }

  /**
   * Get number of items for sale.
   *
   * @return the number of items for sale.
   */
  public int getItemRotationSize() {
    return itemRotationSize;
  }

  /**
   * Sets a new list of possible items to rotate through.
   *
   * @param possibleItems the new set of items to rotate through.
   */
  public void setPossibleItems(List<ReadOnlyItem> possibleItems) {
    this.possibleItems = List.copyOf(possibleItems);
  }

  /**
   * Set a new number of items for sale.
   *
   * @param itemRotationSize the new number of items for sale.
   */
  public void setItemRotationSize(int itemRotationSize) {
    this.itemRotationSize = itemRotationSize;
  }

  /**
   * Randomly select new items for sale.
   * <p>
   * Currently puts them up for 2x their sell price.
   *
   * @param gameTime the update time.
   */
  public void forceItemRotation(ReadOnlyGameTime gameTime) {
    previousRotationTime = ReadOnlyGameTime.copyOf(gameTime);
    itemRotation.clear();
    for (int i = 0; i < itemRotationSize; i++) {
      ReadOnlyItem itemForSale = possibleItems.get(random.nextInt(possibleItems.size()));
      itemRotation.put(itemForSale, itemForSale.getWorth() * 2);
    }
  }

  // The number of items for sale at any given time.
  private int itemRotationSize;
  // The items that can be selected for sale.
  private List<ReadOnlyItem> possibleItems;
  // The last time the items for sale were successfully rotated.
  private ReadOnlyGameTime previousRotationTime;
  // The items currently for sale along with their price.
  private final Map<ReadOnlyItem, Double> itemRotation;
  private static final Random random = new Random();
}
