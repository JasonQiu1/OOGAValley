package oogasalad.fake.api;

import oogasalad.fake.config.LandConfig;
import oogasalad.fake.config.PlantConfig;

public interface GameConfigInterface {

  void addConfig(LandConfig config);

  void addConfig(PlantConfig config);

  void save();
}
