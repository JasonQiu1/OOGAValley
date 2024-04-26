package oogasalad.fake.config.item;

import com.google.firebase.internal.NonNull;
import oogasalad.fake.config.BaseConfig;

public class SeedConfig extends BaseConfig {

  private final double sellPrice;

  @NonNull
  public SeedConfig(String imagePath, String id, double sellPrice) {
    super(imagePath, id);
    this.sellPrice = sellPrice;
  }

  public double getSellPrice() {
    return sellPrice;
  }
}
