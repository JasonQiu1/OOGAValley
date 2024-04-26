package oogasalad.fake.GameObject.Bag.name;

public class ToolItemName implements BagObjectName{
  private String id;

  public ToolItemName(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
