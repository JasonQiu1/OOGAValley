package oogasalad.fake.GameObject.Bag.name;

public class PlantItemName implements BagObjectName{

  private String id;

  public PlantItemName(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }
}
