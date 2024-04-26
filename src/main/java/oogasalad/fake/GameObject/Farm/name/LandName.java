package oogasalad.fake.GameObject.Farm.name;

public class LandName implements FarmObjectName {
  private String id;

  public LandName(String id) {
    this.id = id;
  }


  @Override
  public String getName() {
    return id;
  }
}