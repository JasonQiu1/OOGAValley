package oogasalad.model.gameobject;

import oogasalad.model.api.ReadOnlyItem;

public class Item implements ReadOnlyItem {
  private String itemId;

  @Override
  public String toString() {
    return itemId;
  }

  /**
   * Get the name/id of the item.
   *
   * @returnthe name/id of the item.
   */
  @Override
  public String getName() {
    // TODO: IMPLEMENT
    return null;
  }

  /**
   * Get the image file name that represents the item view. This file lives in `data/images`
   *
   * @return the image file name that represents the item view.
   */
  @Override
  public String getImage() {
    // TODO: IMPLEMENT
    return null;
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
}
