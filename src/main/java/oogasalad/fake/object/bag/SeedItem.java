package oogasalad.fake.object.bag;

import oogasalad.fake.Game;
import oogasalad.fake.config.item.SeedConfig;
import oogasalad.fake.map.Coord;

public class SeedItem extends BagItem {

  private final SeedConfig seedConfig;

  public SeedItem(SeedConfig seedConfig, int number) {
    super(number);
    this.seedConfig = seedConfig;
  }

  @Override
  public boolean interact(Coord coord, Game game) {
    return false;
  }

  @Override
  public boolean consume(Game game) {
    return false;
  }

  @Override
  public boolean sell(Game game) {
    return false;
  }

  public SeedConfig getSeedConfig() {
    return seedConfig;
  }
}
