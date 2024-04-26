package oogasalad.fake.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import oogasalad.fake.api.GameConfigInterface;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;
import oogasalad.fake.json.GameConfigParser;

public class GameConfig implements GameConfigInterface {

  private final Map<String, LandConfig> landConfigMap;

  private final Map<String, PlantConfig> plantConfigMap;

  public GameConfig() {
    System.out.println("default gameConfig not supported");
    assert false;
    landConfigMap = null;
    plantConfigMap = null;
  }

  public GameConfig(String filePath) throws SaveNotValidException, IOException {
    File configFile = getConfigFile(filePath);
    GameConfigParser parser = new GameConfigParser(configFile.getAbsolutePath());
    landConfigMap = parser.getLandConfigs();
    plantConfigMap = parser.getPlantConfigs();

  }


  @Override
  public void addConfig(LandConfig config) {
    this.landConfigMap.put(config.getId(), config);
  }

  @Override
  public void addConfig(PlantConfig config) {
    this.plantConfigMap.put(config.getId(), config);
  }


  @Override
  public void save(String filePath) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(Paths.get(filePath).toFile(), this);
  }

  /**
   * Check if the folder structure is good
   */
  private File getConfigFile(@NotNull String path) throws SaveNotValidException {
    if (!(path.endsWith("save.farm"))) {
      throw new SaveNotValidException(SaveNotValidException.message);
    }
    File file = new File(path);
    String savePath = file.getParent() + "/save/";
    File configFile = new File(savePath + "config.json");
    if (!configFile.isFile()) {
      throw new SaveNotValidException(SaveNotValidException.message);
    }
    return configFile;
  }

  public Map<String, LandConfig> getLandConfigMap() {
    return landConfigMap;
  }

  public Map<String, PlantConfig> getPlantConfigMap() {
    return plantConfigMap;
  }


}
