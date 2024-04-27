package oogasalad.view.shopping.components;

/**
 * This class is responsible for creating the item view that is used to display the items in the
 * shop.
 */
public class ItemView {

  private final String name;
  private final double number;
  private final String url;

  public ItemView(double number, String url, String name) {
    this.number = number;
    this.url = url;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public double getNumber() {
    return number;
  }

  public String getUrl() {
    return url;
  }
}
