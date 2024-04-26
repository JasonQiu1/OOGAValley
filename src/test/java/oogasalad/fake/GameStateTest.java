package oogasalad.fake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.GameConfig;
import oogasalad.fake.object.bag.BagItem;
import oogasalad.fake.object.bag.PlantItem;
import oogasalad.fake.object.bag.SeedItem;
import oogasalad.fake.object.bag.ToolItem;
import org.junit.jupiter.api.Test;

public class GameStateTest {

  String path = "valley_01/save.farm";

  @Test
  void createGameState() throws IOException, SaveNotValidException {
    GameConfig gameConfig = new GameConfig(path);
    List<BagItem> itemList = new ArrayList<>();
    itemList.add(new ToolItem(gameConfig.getToolConfigMap().get("hoe_bag"), 1));
    itemList.add(new SeedItem(gameConfig.getSeedConfigMap().get("wheat_seed_bag"), 1));
    itemList.add(new PlantItem(gameConfig.getPlantItemConfigMap().get("wheat_bag"), 1));

    GameState gameState = new GameState(new GameTime(0,0,0),
        itemList, 100, 100, path);
    gameState.save();
  }
  @Test
  void testStateLoad() throws IOException, SaveNotValidException {
    GameState gameState = new GameState(path);
    gameState.save();
  }
}
