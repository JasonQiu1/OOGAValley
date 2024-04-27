package oogasalad.view.shopping.components;

/**
 * This class is responsible for creating the item view that is used to display the items in the
 * shop.
 */
public class ItemView {

  private double number;
  private String url;

  public ItemView(double number, String url) {
    this.number = number;
    this.url = url;
  }

  public double getNumber() {
    return number;
  }

  public String getUrl() {
    return url;
  }
}
