package oogasalad.fake.config.item;

import oogasalad.fake.config.BaseConfig;
import oogasalad.model.gameplay.GameTime;

public class ToolConfig extends BaseConfig {

  private GameTime timeConsume;

  private int energyConsume;

  public ToolConfig(String imagePath, String id) {
    super(imagePath, id);
  }

  public int getEnergyConsume() {
    return energyConsume;
  }

  public GameTime getTimeConsume() {
    return timeConsume.copy();
  }
}
