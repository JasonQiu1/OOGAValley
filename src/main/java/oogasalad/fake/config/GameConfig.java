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
import oogasalad.fake.config.item.PlantItemConfig;
import oogasalad.fake.config.item.SeedConfig;
import oogasalad.fake.config.item.ToolConfig;
import oogasalad.fake.json.GameConfigParser;
import oogasalad.fake.object.bag.BagItem;
import oogasalad.fake.object.bag.PlantItem;
import oogasalad.fake.object.bag.SeedItem;

public class GameConfig implements GameConfigInterface {

  private final Map<String, LandConfig> landConfigMap;
  private final Map<String, PlantConfig> plantConfigMap;
  private final Map<String, PlantItemConfig> plantItemConfigMap;
  private final Map<String, ToolConfig> toolConfigMap;
  private final Map<String, SeedConfig> seedConfigMap;
  private final String configFilePath;

  public GameConfig() {
    System.out.println("default gameConfig not supported");
    assert false;
    landConfigMap = null;
    plantConfigMap = null;
    configFilePath = null;
    plantItemConfigMap = null;
    toolConfigMap = null;
    seedConfigMap = null;
  }

  public GameConfig(String filePath) throws SaveNotValidException, IOException {
    configFilePath = getConfigFile(filePath).getAbsolutePath();
    GameConfigParser parser = new GameConfigParser(configFilePath);
    landConfigMap = parser.getLandConfigs();
    plantConfigMap = parser.getPlantConfigs();
    plantItemConfigMap = parser.getPlantItemConfigs();
    toolConfigMap = parser.getToolConfigs();
    seedConfigMap = parser.getSeedConfigs();
  }


  @Override
  public void addConfig(LandConfig config) {
    landConfigMap.put(config.getId(), config);
  }

  @Override
  public void addConfig(PlantConfig config) {
    plantConfigMap.put(config.getId(), config);
  }

  @Override
  public void addConfig(PlantItemConfig config) {
    plantItemConfigMap.put(config.getId(), config);
  }

  @Override
  public void addConfig(ToolConfig config) {
    toolConfigMap.put(config.getId(), config);
  }

  @Override
  public void addConfig(SeedConfig config) {
    seedConfigMap.put(config.getId(), config);
  }


  @Override
  public void save() throws IOException, SaveNotValidException {
    validate();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writerWithDefaultPrettyPrinter()
        .writeValue(Paths.get(configFilePath).toFile(), this);
  }


  @Override
  public void save(String fileName) throws IOException, SaveNotValidException {
    validate();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(fileName).toFile(), this);
  }

  public BagItem getItemFromId(String id, int number) {
    if (plantItemConfigMap.containsKey(id)) {
      return new PlantItem(plantItemConfigMap.get(id), number);
    }
    if (seedConfigMap.containsKey(id)) {
      return new SeedItem(seedConfigMap.get(id), number);
    }
    return null;
  }


  public Map<String, LandConfig> getLandConfigMap() {
    return landConfigMap;
  }

  public Map<String, PlantConfig> getPlantConfigMap() {
    return plantConfigMap;
  }

  public Map<String, PlantItemConfig> getPlantItemConfigMap() {
    return plantItemConfigMap;
  }

  public Map<String, ToolConfig> getToolConfigMap() {
    return toolConfigMap;
  }

  public Map<String, SeedConfig> getSeedConfigMap() {
    return seedConfigMap;
  }

  private void validate() throws SaveNotValidException {
    GameConfigValidator validator = new GameConfigValidator(this);
    validator.validate();
  }

  /**
   * Check if the folder structure is good
   */
  private File getConfigFile(@NotNull String path) throws SaveNotValidException {
    if (!(path.endsWith("save.farm"))) {
      throw new SaveNotValidException(SaveNotValidException.message);
    }
    File file = new File(path);
    File configFile = new File(file.getParent() + "/config.json");
    if (!configFile.isFile()) {
      throw new SaveNotValidException(SaveNotValidException.message);
    }
    return configFile;
  }
}
