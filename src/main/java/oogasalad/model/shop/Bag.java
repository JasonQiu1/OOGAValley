package oogasalad.model.shop;

import java.util.HashMap;
import java.util.Map;

public class Bag {

  private Map<BagItemModel, Integer> itemMap;

  public Bag() {
    itemMap = new HashMap<>();
  }

  public void addItem(BagItemModel bagItemModel, int quantity) {
    boolean itemExists = false;
    for (Map.Entry<BagItemModel, Integer> entry : itemMap.entrySet()) {
      BagItemModel existingItem = entry.getKey();
      if (existingItem.getUrl().equals(bagItemModel.getUrl())) {
        itemExists = true;
        entry.setValue(entry.getValue() + quantity);
        break;
      }
    }
    if (!itemExists) {
      itemMap.put(bagItemModel, quantity);
    }
  }

  public Map<BagItemModel, Integer> getItemMap() {
    return itemMap;
  }
}
