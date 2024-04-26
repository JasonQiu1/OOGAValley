package oogasalad.fake.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.farm.LandConfig;
import oogasalad.fake.config.farm.PlantConfig;
import oogasalad.fake.json.GameMapConfigParser;
import oogasalad.fake.object.Land;
import oogasalad.fake.object.Plant;

public class GameMap {

  private final int height;
  private final int width;
  private final Map<Coord, Land> landPositionMap;
  private final Map<Coord, Plant> plantPositionMap;
  private final String savePath;

  public GameMap(String filePath) throws IOException, SaveNotValidException {
    if (!(filePath.endsWith("save.farm"))) {
      throw new SaveNotValidException(SaveNotValidException.message);
    }
    File file = new File(filePath);
    savePath = file.getParent() + "/save/map.json";
    GameMapConfigParser gameMapConfigParser = new GameMapConfigParser(savePath);
    this.height = gameMapConfigParser.getHeight();
    this.width = gameMapConfigParser.getWidth();
    this.landPositionMap = gameMapConfigParser.getLandPositionMapCreate();
    this.plantPositionMap = gameMapConfigParser.getPlantPositionMapCreate();

  }

  public void save() throws IOException, SaveNotValidException {

    save(savePath);
  }

  public void save(String fileName) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writerWithDefaultPrettyPrinter()
        .writeValue(Paths.get(fileName).toFile(), this);
  }

  private void validate() {

  }

  public void setLand(Coord coord, LandConfig landConfig) {
    Land land = new Land(landConfig);
    landPositionMap.put(coord, land);
  }

  public void setPlant(Coord coord, PlantConfig plantConfig) {
    Plant plant = new Plant(plantConfig, null);
    plantPositionMap.put(coord, plant);
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public Map<Coord, Land> getLandPositionMap() {
    return landPositionMap;
  }

  public Map<Coord, Plant> getPlantPositionMap() {
    return plantPositionMap;
  }
}
