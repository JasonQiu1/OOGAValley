package oogasalad.fake.object;

import oogasalad.fake.config.farm.LandConfig;

public class Land {

  private final LandConfig landConfig;

  public Land(LandConfig landConfig) {
    this.landConfig = landConfig;
  }

  public LandConfig getLandConfig() {
    return landConfig;
  }
}
