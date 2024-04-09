package oogasalad.Game.GameModel.shop;

/**
 * Items the shop provides
 */
public class SellItem {

  private final int prices;

  private final String url;

  public SellItem(int prices, String url) {
    this.prices = prices;
    this.url = url;
  }

  public int getPrices() {
    return prices;
  }

  public String getUrl() {
    return url;
  }


}
