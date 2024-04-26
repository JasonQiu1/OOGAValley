package oogasalad.fake.api;

import java.io.IOException;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;
import oogasalad.fake.config.item.PlantItemConfig;
import oogasalad.fake.config.item.SeedConfig;
import oogasalad.fake.config.item.ToolConfig;

public interface GameConfigInterface {

  void addConfig(LandConfig config);

  void addConfig(PlantConfig config);

  void addConfig(PlantItemConfig config);

  void addConfig(ToolConfig config);

  void addConfig(SeedConfig config);


  void save() throws IOException, SaveNotValidException;


  void save(String fileName) throws IOException, SaveNotValidException;
}
