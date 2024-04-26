package oogasalad.view.shopping.components;

import oogasalad.fake.object.bag.BagItem;

/**
 * Items the shop provides
 */
public class SellItem {

  private final double prices;

  private final String url;
  private final BagItem bagItem;

  public SellItem(BagItem bagItem) {
    this.bagItem = bagItem;
    this.prices = bagItem.ifSellReturnPrice();
    this.url = bagItem.getConfig().getImagePath();
  }

  public double getPrices() {
    return prices;
  }

  public String getUrl() {
    return url;
  }

  public BagItem getBagItem() {
    return bagItem;
  }
}
