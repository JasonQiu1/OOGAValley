package oogasalad.fake.GameObject.Bag.name;

public class SeedItemName implements BagObjectName{
  private String id;

  public SeedItemName(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

}
