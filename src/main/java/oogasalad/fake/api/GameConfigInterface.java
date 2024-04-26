package oogasalad.fake.api;

import java.io.IOException;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;

public interface GameConfigInterface {

  void addConfig(LandConfig config);

  void addConfig(PlantConfig config);


  void save() throws IOException;
}
