package oogasalad.fake.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.object.bag.BagItem;
import oogasalad.fake.object.bag.PlantItem;
import oogasalad.fake.object.bag.SeedItem;
import oogasalad.fake.object.bag.ToolItem;

public class GameStateParser {

  private final GameTime gameTime;
  private final List<BagItem> itemList;
  private final double money;
  private final double energy;

  public GameStateParser(String path) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> rawConfig = objectMapper.readValue(
        Paths.get(path).toFile(),
        new TypeReference<>() {
        });
    money = (double) rawConfig.get("money");
    energy = (double) rawConfig.get("energy");
    Map<String, Integer> gameTimeInfo = (Map<String, Integer>) rawConfig.get("gameTime");
    gameTime = new GameTime(gameTimeInfo.get("day"),
        gameTimeInfo.get("hour"),
        gameTimeInfo.get("minute"));
    itemList = new ArrayList<>();
    List<Map<String, Object>> itemListInfo = (List<Map<String, Object>>) rawConfig.get("itemList");
    for (Map<String, Object> item : itemListInfo) {
      int number = (int) item.get("number");
      String configName = getConfigEntries(item);
      switch (configName) {
        case "toolConfig":
          itemList.add(new ToolItem(
              ParserTools.createToolConfig((Map<String, Object>) item.get(configName)), number));
          break;
        case "seedConfig":
          itemList.add(new SeedItem(
              ParserTools.createSeedConfig((Map<String, Object>) item.get(configName)), number));
          break;
        case "plantItemConfig":
          itemList.add(new PlantItem(
              ParserTools.createPlantItemConfig((Map<String, Object>) item.get(configName)), number));
          break;
      }
    }
  }

  public static String getConfigEntries(Map<String, Object> map) {
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      if (entry.getKey().endsWith("Config")) {
        return entry.getKey();
      }
    }
    return null;
  }

  public GameTime getGameTime() {
    return gameTime;
  }

  public List<BagItem> getItemList() {
    return itemList;
  }

  public double getMoney() {
    return money;
  }

  public double getEnergy() {
    return energy;
  }

  public static void main(String[] args) {
    try {
      GameStateParser gameStateParser = new GameStateParser("valley_01/state.json");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
