package oogasalad.fake.object;

import oogasalad.fake.config.LandConfig;

public abstract class BaseLand {

  private final LandConfig landConfig;

  public BaseLand(LandConfig landConfig) {
    this.landConfig = landConfig;
  }
}
