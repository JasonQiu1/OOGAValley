package oogasalad.fake;

import java.io.File;
import java.io.IOException;
import oogasalad.fake.api.GameInterface;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.GameConfig;
import oogasalad.fake.map.GameMap;

public class Game implements GameInterface {

  private final GameMap gameMap;

  private final GameState gameState;

  private final GameConfig gameConfig;

  public Game() {
    // not supported right now
    gameConfig = null;
    gameState = null;
    gameMap = null;
  }

  /**
   * Create a game with a specified file Name
   *
   * @param fileName
   */
  public Game(String fileName) throws IOException, SaveNotValidException {
    gameMap = new GameMap(fileName);
    gameConfig = new GameConfig(fileName);
    gameState = new GameState(fileName);
  }


  @Override
  public GameConfig getGameConfig() {
    return gameConfig;
  }

  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public GameMap getGameMap() {
    return gameMap;
  }

  /**
   * The saved file
   *
   * @param folderName the folder to save the file to
   * @throws IOException
   * @throws SaveNotValidException
   */
  @Override
  public void save(String folderName) throws IOException, SaveNotValidException {
    gameMap.save(folderName + "/map.json");
    gameConfig.save(folderName + "/config.json");
    gameState.save(folderName + "/state.json");
    File file = new File(folderName + "/save.farm");
    file.createNewFile();

  }
}
