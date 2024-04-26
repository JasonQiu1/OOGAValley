package oogasalad.fake.config.item;

import com.google.firebase.internal.NonNull;
import oogasalad.fake.GameTime;
import oogasalad.fake.config.BaseConfig;


public class ToolConfig extends BaseConfig {

  private final GameTime timeConsume;

  private final double energyConsume;

  @NonNull
  public ToolConfig(String imagePath, String id, GameTime timeConsumed, double energyConsumed) {
    super(imagePath, id);
    this.timeConsume = timeConsumed;
    this.energyConsume = energyConsumed;
  }

  public double getEnergyConsume() {
    return energyConsume;
  }

  public GameTime getTimeConsume() {
    return timeConsume.copy();
  }
}
