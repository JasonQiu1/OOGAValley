package oogasalad.fake.json;

import java.util.HashMap;
import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;
import oogasalad.fake.config.item.PlantItemConfig;
import oogasalad.fake.config.item.SeedConfig;
import oogasalad.fake.config.item.ToolConfig;

public class ParserTools {

  public static LandConfig createLandConfig(Map<String, Object> landInfo) {
    Map<String, String> transformLandMap = new HashMap<>();
    Map<String, String> transformLand = (Map<String, String>) landInfo.get("transFromLand");
    for (Map.Entry<String, String> entry : transformLand.entrySet()) {
      transformLandMap.put((String) entry.getKey(), entry.getValue());
    }
    Map<String, String> seedGrown = (Map<String, String>) landInfo.get("seedGrown");
    Map<String, String> seedGrownMap = new HashMap<>(seedGrown);
    LandConfig landConfig = new LandConfig((String) landInfo.get("imagePath"),
        (String) landInfo.get("id"),
        transformLand,
        seedGrownMap);
    return landConfig;
  }

  public static PlantConfig createPlantConfig(Map<String, Object> plantInfo) {
    Map<String, Map<String, Integer>> dropMap = (Map<String, Map<String, Integer>>)
        plantInfo.get("dropMap");
    Map<String, Integer> gameTime = (Map<String, Integer>) plantInfo.get("growthTime");
    GameTime game = new GameTime(gameTime.get("day"), gameTime.get("hour"),
        gameTime.get("minute"));
    PlantConfig plantConfig = new PlantConfig((String) plantInfo.get("imagePath"),
        (String) plantInfo.get("id"),
        dropMap,
        game);
    return plantConfig;
  }

  public static PlantItemConfig createPlantItemConfig(Map<String, Object> plantItemInfo) {
    PlantItemConfig plantItemConfig = new PlantItemConfig((String) plantItemInfo.get("imagePath"),
        (String) plantItemInfo.get("id"),
        (double) plantItemInfo.get("sellPrice"), (double) plantItemInfo.get("eatEnergy"));
    return plantItemConfig;
  }

  public static ToolConfig createToolConfig(Map<String, Object> toolInfo) {
    Map<String, Integer> gameTime = (Map<String, Integer>) toolInfo.get("timeConsume");
    GameTime game = new GameTime(gameTime.get("day"), gameTime.get("hour"),
        gameTime.get("minute"));
    return new ToolConfig((String) toolInfo.get("imagePath"),
        (String) toolInfo.get("id"), game,
        (double) toolInfo.get("energyConsume"));
  }

  public static SeedConfig createSeedConfig(Map<String, Object> seedInfo) {
    return new SeedConfig((String) seedInfo.get("imagePath"),
        (String) seedInfo.get("id"),
        (double) seedInfo.get("sellPrice"));

  }

}
