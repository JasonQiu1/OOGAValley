package oogasalad.fake.GameObject.Farm.name;


public class PlantName implements FarmObjectName {
  private String id;

  public PlantName(String id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return id;
  }
}