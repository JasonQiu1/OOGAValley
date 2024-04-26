package oogasalad.fake.object.bag;

import oogasalad.fake.Game;
import oogasalad.fake.map.Coord;

public abstract class BagItem {

  private int number;

  public abstract void interact(Coord coord, Game game);
}
