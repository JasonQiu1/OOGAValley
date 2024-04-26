package oogasalad.fake.config;

import com.google.firebase.database.annotations.NotNull;
import java.io.File;
import java.util.Map;
import oogasalad.fake.api.GameConfigInterface;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;
import oogasalad.fake.config.item.ToolConfig;

public class GameConfig implements GameConfigInterface {

  private final Map<String, LandConfig> landConfigMap;

  private final Map<String, PlantConfig> plantConfigMap;


  private final Map<String, ToolConfig> toolConfigMap;

  public GameConfig() {
    System.out.println("default gameConfig not supported");
    assert false;
    landConfigMap = null;
    plantConfigMap = null;
    toolConfigMap = null;
  }

  public GameConfig(String filePath) throws SaveNotValidException {
    File configFile = getConfigFile(filePath);
    landConfigMap = initLandConfig(configFile);
    plantConfigMap = initPlantConfig(configFile);
    toolConfigMap = null;
  }

  @Override
  public void addConfig(LandConfig config) {
    this.landConfigMap.put(config.getId(), config);
  }

  @Override
  public void addConfig(PlantConfig config) {
    this.plantConfigMap.put(config.getId(), config);
  }


  /**
   * Save the current config validate the data before saving
   */
  @Override
  public void save() {

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

  //TODO: implement this method
  private Map<String, LandConfig> initLandConfig(File configFile) {
    return null;
  }

  //TODO: implement this method
  private Map<String, PlantConfig> initPlantConfig(File configFile) {
    return null;
  }


}
