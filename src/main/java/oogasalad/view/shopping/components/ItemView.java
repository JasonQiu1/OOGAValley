package oogasalad.view.shopping.components;

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
