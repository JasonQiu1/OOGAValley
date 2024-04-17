package oogasalad.model.shop;

public class BagItemModel {

  private final String url;
  private final ItemType type;

  public BagItemModel(String url, ItemType type) {
    this.url = url;
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public ItemType getType() {
    return type;
  }
}
