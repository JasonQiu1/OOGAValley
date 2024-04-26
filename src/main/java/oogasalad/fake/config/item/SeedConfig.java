package oogasalad.fake.config.item;

import oogasalad.fake.config.BaseConfig;

public class SeedConfig extends BaseConfig {
  private final double sellPrice;

  public SeedConfig(String imagePath, String id, double sellPrice) {
    super(imagePath, id);
    this.sellPrice = sellPrice;
  }

  public double getSellPrice() {
    return sellPrice;
  }
}
