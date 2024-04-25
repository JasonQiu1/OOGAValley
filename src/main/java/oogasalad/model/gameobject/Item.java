package oogasalad.model.gameobject;

import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.data.GameConfiguration;

public class Item implements ReadOnlyItem {

  private final String itemId;

  public Item(String itemId) {
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
    return getItemProperties().getDouble("worth");
  }

  /**
   * Get the image file name that represents the item view. This file lives in `data/images`
   *
   * @return the image file name that represents the item view.
   */
  @Override
  public String getImagePath() {
    return getItemProperties().getString("image");
  }

  private ReadOnlyProperties getItemProperties() {
    return GameConfiguration.getConfigurablesStore().getConfigurableProperties(itemId);
  }

  /**
   * equals for item comparision, this is useful for the update of the view, if two objects have the
   * same name, it should be the same, and there is no need to
   *
   * @param obj
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Item other = (Item) obj;
    if (itemId == null) {
      return other.itemId == null;
    } else {
      return itemId.equals(other.itemId);
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
    return result;
  }

}
