package oogasalad.fake.object.bag;

import oogasalad.fake.Game;
import oogasalad.fake.config.item.SeedConfig;
import oogasalad.fake.map.Coord;

public class SeedItem extends BagItem{
  private SeedConfig seedConfig;

  public SeedItem(SeedConfig seedConfig) {
    this.seedConfig = seedConfig;
  }

  @Override
  public void interact(Coord coord, Game game) {

  }

  public SeedConfig getSeedConfig() {
    return seedConfig;
  }
}
