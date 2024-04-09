package oogasalad.Game.GameModel.shop;

public class BagItemModel {
  private String url;
  private ItemType type;

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
