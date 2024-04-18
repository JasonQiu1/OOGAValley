package oogasalad.model.api;

/**
 * Provides an interface to get information about an item.
 *
 * @author Jason Qiu
 */
public interface ReadOnlyItem {

  /**
   * Get the name/id of the item.
   *
   * @return the name/id of the item.
   */
  String getName();

  /**
   * Get the image file name that represents the item view. This file lives in `data/images`
   *
   * @return the image file name that represents the item view.
   */
  String getImage();

  /**
   * Get how much money the item is worth.
   *
   * @return how much money the item is worth.
   */
  double getWorth();
}
