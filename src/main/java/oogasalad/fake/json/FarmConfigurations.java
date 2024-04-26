package oogasalad.fake.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;

public class FarmConfigurations {

  private Map<String, LandConfig> land;
  private Map<String, PlantConfig> plant;

  public FarmConfigurations(Map<String, LandConfig> land, Map<String, PlantConfig> plant) {
    this.land = land;
    this.plant = plant;
  }

  public Map<String, LandConfig> getLand() {
    return land;
  }

  public void setLand(
      Map<String, LandConfig> land) {
    this.land = land;
  }

  public Map<String, PlantConfig> getPlant() {
    return plant;
  }

  public void setPlant(
      Map<String, PlantConfig> plant) {
    this.plant = plant;
  }
  private String toJson() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(this);
  }

  public void toJsonFile(String filePath) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(Paths.get(filePath).toFile(), this);
  }
}
