package oogasalad.fake.config.item;

import com.google.firebase.internal.NonNull;
import oogasalad.fake.config.BaseConfig;

public class PlantItemConfig extends BaseConfig {

  private final double sellPrice;
  private final double eatEnergy;

  @NonNull
  public PlantItemConfig(String imagePath, String id, double sellPrice, double eatEnergy) {
    super(imagePath, id);
    this.sellPrice = sellPrice;
    this.eatEnergy = eatEnergy;
  }

  public double getSellPrice() {
    return sellPrice;
  }

  public double getEatEnergy() {
    return eatEnergy;
  }
}
