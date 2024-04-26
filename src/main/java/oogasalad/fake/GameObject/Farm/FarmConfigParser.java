package oogasalad.fake.GameObject.Farm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import oogasalad.fake.GameObject.Bag.name.SeedItemName;
import oogasalad.fake.GameObject.Bag.name.ToolItemName;
import oogasalad.fake.GameObject.Farm.config.LandConfig;
import oogasalad.fake.GameObject.Farm.config.PlantConfig;
import oogasalad.fake.GameObject.Farm.name.LandName;

public class FarmConfigParser {

  private static final String JSON_FILE_PATH = "src/main/java/oogasalad/fake/GameObject/Farm/FarmConfig.json";

  public static FarmConfigurations parseFarmConfigurations() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Map<String, Object>> rawConfig = objectMapper.readValue(
        Paths.get(JSON_FILE_PATH).toFile(),
        new TypeReference<Map<String, Map<String, Object>>>() {
        });

    Map<String, LandConfig> landConfigs = createLandConfig(rawConfig);
    Map<String, PlantConfig> plantConfigs = new HashMap<>();
    System.out.println(rawConfig);

    return new FarmConfigurations(landConfigs, plantConfigs);
  }
  public static Map<String, LandConfig> createLandConfig(Map<String, Map<String, Object>> rawConfig){
    Map<String, LandConfig> landConfigs = new HashMap<>();

    Map<String, Object> lands = (Map<String, Object>) rawConfig.get("land");
    for (Map.Entry<String, Object> landEntry : lands.entrySet()) {
      String landKey = landEntry.getKey();
      Map<String, Object> landInfo = (Map<String, Object>) landEntry.getValue();

      LandConfig landConfig = new LandConfig();
      landConfig.setImagePath((String) landInfo.get("path"));
      landConfig.setLandName(new LandName(landKey));

      Map<ToolItemName, LandName> transformLandMap = new HashMap<>();
      Map<String, String> transformLand = (Map<String, String>) landInfo.get("transformLand");
      for (Map.Entry<String, String> entry : transformLand.entrySet()) {
        ToolItemName tool = new ToolItemName(entry.getKey());
        LandName land = new LandName(entry.getValue());
        transformLandMap.put(tool, land);
      }
      landConfig.setTransFromLand(transformLandMap);

      Map<SeedItemName, LandName> seedGrownMap = new HashMap<>();
      Map<String, String> seedGrown = (Map<String, String>) landInfo.get("seedGrown");
      for (Map.Entry<String, String> entry : seedGrown.entrySet()) {
        SeedItemName seed = new SeedItemName(entry.getKey());
        LandName grownLand = new LandName(entry.getValue());
        seedGrownMap.put(seed, grownLand);
      }
      landConfig.setSeedGrown(seedGrownMap);
      landConfigs.put(landKey, landConfig);
    }
    return landConfigs;
  }

  public static void main(String[] args) {
    try {
      FarmConfigurations farmConfigurations = parseFarmConfigurations();
      farmConfigurations.getLand().forEach((key, value) -> {
        System.out.println("Land Name: " + value.getLandName());
        System.out.println(key + " : " + value.getImagePath());
        System.out.println("Transform Land: " + value.getTransFromLand());
        System.out.println("Seed Grown: " + value.getSeedGrown());
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

