package oogasalad.fake.api;

import oogasalad.fake.config.LandConfig;
import oogasalad.fake.config.PlantConfig;

public interface GameConfigInterface {

  void addObject(LandConfig landConfig);

  void addObject(PlantConfig plantConfig);

  void save();
}
