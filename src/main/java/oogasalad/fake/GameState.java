package oogasalad.fake;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.object.bag.BagItem;

public class GameState {

  private final GameTime gameTime;
  private final List<BagItem> itemList;
  private int money;
  private int energy;
  private final String savePath;

  public GameState(GameTime gameTime, List<BagItem> itemList, int money, int energy,
      String filePath) throws SaveNotValidException {
    this.gameTime = gameTime;
    this.itemList = itemList;
    this.money = money;
    this.energy = energy;
    savePath = findSavePath(filePath);
  }

  private String findSavePath(String filePath) throws SaveNotValidException {
    if (!(filePath.endsWith("save.farm"))) {
      throw new SaveNotValidException(SaveNotValidException.message);
    }
    File file = new File(filePath);
    return file.getParent() + "/save/state.json";
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

  public int getMoney() {
    return money;
  }

  public int getEnergy() {
    return energy;
  }

  public String getSavePath() {
    return savePath;
  }
}
