package oogasalad.fake.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.object.bag.BagItem;

public class GameStateParser {

  private GameTime gameTime;
  private List<BagItem> itemList;
  private int money;
  private int energy;

  public GameStateParser(String path) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> rawConfig = objectMapper.readValue(
        Paths.get(path).toFile(),
        new TypeReference<>() {
        });
    money = (int) rawConfig.get("money");
    energy = (int) rawConfig.get("energy");
    Map<String, String> gameTimeInfo = (Map<String, String>) rawConfig.get("gameTime");
    System.out.println(gameTimeInfo);
    GameTime gameTime = new GameTime(Integer.parseInt(gameTimeInfo.get("day")),
        Integer.parseInt(gameTimeInfo.get("hour")),
        Integer.parseInt(gameTimeInfo.get("minute")));
    System.out.println(rawConfig.get("itemList"));
    List<Map<String, Object>> itemListInfo = (List<Map<String, Object>>) rawConfig.get("itemList");
    System.out.println(itemListInfo);
  }

  public static void main(String[] args) {
    try {
      GameStateParser gameStateParser = new GameStateParser("valley_01/save/state.json");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
