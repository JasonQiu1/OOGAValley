package oogasalad.fake.object.bag;

import oogasalad.fake.Game;
import oogasalad.fake.config.item.ToolConfig;
import oogasalad.fake.map.Coord;

public class ToolItem extends BagItem {

  private final ToolConfig toolConfig;

  public ToolItem(ToolConfig toolConfig, int number) {
    super(number);
    this.toolConfig = toolConfig;
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

  public ToolConfig getToolConfig() {
    return toolConfig;
  }
}
