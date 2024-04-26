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
  public void interact(Coord coord, Game game) {

  }

  public ToolConfig getToolConfig() {
    return toolConfig;
  }
}
