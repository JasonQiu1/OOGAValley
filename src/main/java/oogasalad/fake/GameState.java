package oogasalad.fake;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.json.GameStateParser;
import oogasalad.fake.object.bag.BagItem;

public class GameState {

  private final GameTime gameTime;
  private final List<BagItem> itemList;

  private double money;
  private double energy;
  private final String savePath;

  public GameState(GameTime gameTime, List<BagItem> itemList, double money, double energy,
      String filePath) throws SaveNotValidException {
    this.gameTime = gameTime;
    this.itemList = itemList;
    this.money = money;
    this.energy = energy;
    savePath = findSavePath(filePath);
  }

  public GameState(String filePath) throws IOException, SaveNotValidException {
    savePath = findSavePath(filePath);
    GameStateParser gameStateParser = new GameStateParser(savePath);
    this.gameTime = gameStateParser.getGameTime();
    this.itemList = gameStateParser.getItemList();
    this.money = gameStateParser.getMoney();
    this.energy = gameStateParser.getEnergy();
  }

  private String findSavePath(String filePath) throws SaveNotValidException {
    if (!(filePath.endsWith("save.farm"))) {
      throw new SaveNotValidException(SaveNotValidException.message);
    }
    File file = new File(filePath);
    return file.getParent() + "/state.json";
  }

  public void save() throws IOException, SaveNotValidException {
    save(savePath);
  }

  public void save(String fileName) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writerWithDefaultPrettyPrinter()
        .writeValue(Paths.get(fileName).toFile(), this);
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

  public void addMoney(double money) {
    this.money += money;
  }

  public void addEnergy(double energy) {
    this.energy += energy;
  }

  public String getSavePath() {
    return savePath;
  }
}
