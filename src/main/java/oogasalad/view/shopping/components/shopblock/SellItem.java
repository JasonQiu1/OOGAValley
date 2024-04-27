package oogasalad.view.shopping.components.shopblock;

/**
 * Items the shop provides
 */
public class SellItem {

  private final double prices;

  private final String url;

  public SellItem(double prices, String url) {
    this.prices = prices;
    this.url = url;
  }

  public double getPrices() {
    return prices;
  }

  public String getUrl() {
    return url;
  }


}
