package oogasalad.fake.object.bag;

import oogasalad.fake.Game;
import oogasalad.fake.config.BaseConfig;
import oogasalad.fake.map.Coord;

public abstract class BagItem {

  private int number;

  private final String id;

  public BagItem(int number, String id) {
    this.number = number;
    this.id = id;
  }

  public int getNumber() {
    return number;
  }

  public void addNumber(int num) {
    this.number += num;
  }

  public abstract boolean interact(Coord coord, Game game);

  public abstract boolean consume(Game game);

  public abstract boolean sell(Game game);

  public abstract BaseConfig getConfig();

  public abstract boolean ifSell();

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return id + ": " + number;
  }
}
