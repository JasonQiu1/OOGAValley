package oogasalad.fake.object.bag;

import oogasalad.fake.Game;
import oogasalad.fake.config.item.PlantItemConfig;
import oogasalad.fake.map.Coord;

public class PlantItem extends BagItem {

  private final PlantItemConfig plantItemConfig;

  public PlantItem(PlantItemConfig plantItemConfig) {
    this.plantItemConfig = plantItemConfig;
  }

  @Override
  public void interact(Coord coord, Game game) {

  }

  public PlantItemConfig getPlantItemConfig() {
    return plantItemConfig;
  }
}
