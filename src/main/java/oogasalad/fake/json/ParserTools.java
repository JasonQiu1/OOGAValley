package oogasalad.fake.json;

import java.util.HashMap;
import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;

public class ParserTools {
  public static LandConfig createLandConfig(Map<String, Object> landInfo) {
    Map<String, String> transformLandMap = new HashMap<>();
    Map<String, String> transformLand = (Map<String, String>) landInfo.get("transFromLand");
    for (Map.Entry<String, String> entry : transformLand.entrySet()) {
      transformLandMap.put((String) entry.getKey(), entry.getValue());
    }
    Map<String, String> seedGrown = (Map<String, String>) landInfo.get("seedGrown");
    Map<String, String> seedGrownMap = new HashMap<>(seedGrown);
    LandConfig landConfig = new LandConfig((String) landInfo.get("imagePath"), (String) landInfo.get("id"),
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

}
