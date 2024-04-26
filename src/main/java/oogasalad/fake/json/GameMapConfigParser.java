package oogasalad.fake.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import oogasalad.fake.GameTime;
import oogasalad.fake.map.Coord;
import oogasalad.fake.object.Land;
import oogasalad.fake.object.Plant;

public class GameMapConfigParser {

  private Map<Coord, Land> landPositionMapCreate;
  private Map<Coord, Plant> plantPositionMapCreate;
  private final int height;
  private final int width;

  public GameMapConfigParser(String path) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> rawConfig = objectMapper.readValue(
        Paths.get(path).toFile(),
        new TypeReference<>() {
        });
    height = (int) rawConfig.get("height");
    width = (int) rawConfig.get("width");
    createLandPositionMap(rawConfig);
    createPlantPositionMap(rawConfig);
  }

  public void createPlantPositionMap(Map<String, Object> rawConfig) {
    plantPositionMapCreate = new HashMap<>();
    Map<String, Map<String, Object>> plantPositionMap = (Map<String, Map<String, Object>>) rawConfig
        .get("plantPositionMap");
    for (Map.Entry<String, Map<String, Object>> entry : plantPositionMap.entrySet()) {
      Coord coord = parseCoord(entry.getKey());
      Map<String, Object> plantInfo = (Map<String, Object>) entry.getValue().get("plantConfig");
      Map<String, String> plantTimeInfo = (Map<String, String>) plantInfo.get("plantTime");
      GameTime plantTime = createGameTime(plantTimeInfo);
      plantPositionMapCreate.put(coord,
          new Plant(ParserTools.createPlantConfig(plantInfo), plantTime));
    }
  }

  public GameTime createGameTime(Map<String, String> plantTimeInfo) {
    GameTime plantTime = null;
    if (plantTimeInfo != null) {
      plantTime = new GameTime(Integer.parseInt(plantTimeInfo.get("day")),
          Integer.parseInt(plantTimeInfo.get("hour")),
          Integer.parseInt(plantTimeInfo.get("minute")));
    }
    return plantTime;
  }

  public void createLandPositionMap(Map<String, Object> rawConfig) {
    landPositionMapCreate = new HashMap<>();
    Map<String, Map<String, Object>> landPositionMap = (Map<String, Map<String, Object>>) rawConfig
        .get("landPositionMap");
    for (Map.Entry<String, Map<String, Object>> entry : landPositionMap.entrySet()) {
      Coord coord = parseCoord(entry.getKey());
      Map<String, Object> landInfo = (Map<String, Object>) entry.getValue().get("landConfig");
      landPositionMapCreate.put(coord, new Land(ParserTools.createLandConfig(landInfo)));
    }
  }

  private static Coord parseCoord(String str) {
    str = str.replaceAll("Coord", "").replace("[", "").replace("]", "");
    String[] parts = str.split(", ");
    int x = 0, y = 0;
    for (String part : parts) {
      String[] keyValue = part.split("=");
      if ("x".equals(keyValue[0])) {
        x = Integer.parseInt(keyValue[1]);
      } else if ("y".equals(keyValue[0])) {
        y = Integer.parseInt(keyValue[1]);
      }
    }
    return new Coord(x, y);
  }

  public Map<Coord, Land> getLandPositionMapCreate() {
    return landPositionMapCreate;
  }

  public Map<Coord, Plant> getPlantPositionMapCreate() {
    return plantPositionMapCreate;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public static void main(String[] args) {
    try {
      GameMapConfigParser gameMapConfigParser = new GameMapConfigParser("valley_01/save/map.json");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
