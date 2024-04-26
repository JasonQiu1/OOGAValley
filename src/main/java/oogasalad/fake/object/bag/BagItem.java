package oogasalad.fake.object.bag;

import oogasalad.fake.Game;
import oogasalad.fake.config.BaseConfig;
import oogasalad.fake.map.Coord;

public abstract class BagItem {

  private final int number;

  public BagItem(int number) {
    this.number = number;
  }

  public int getNumber() {
    return number;
  }

  public abstract boolean interact(Coord coord, Game game);

  public abstract boolean consume(Game game);

  public abstract boolean sell(Game game);
  public abstract BaseConfig getConfig();
}
