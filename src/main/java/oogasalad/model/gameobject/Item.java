package oogasalad.model.gameobject;

import oogasalad.model.api.ReadOnlyItem;

public class Item implements ReadOnlyItem {
  private final String itemId;

  public Item (String itemId) {
    this.itemId = itemId;
  }

  /**
   * Get the name/id of the item.
   *
   * @return the name/id of the item.
   */
  @Override
  public String getName() {
    return itemId;
  }

  /**
   * Get how much money the item is worth.
   *
   * @return how much money the item is worth.
   */
  @Override
  public double getWorth() {
    // TODO: IMPLEMENT
    return 0;
  }

  /**
   * Get the image file name that represents the item view. This file lives in `data/images`
   *
   * @return the image file name that represents the item view.
   */
  @Override
  public String getImagePath() {
    // TODO: IMPLEMENT
    return null;
  }
}
