package oogasalad.model.data;

import java.io.IOException;
import java.nio.file.Paths;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyGameState;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyGameWorld;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.model.api.exception.BadGsonLoadException;
import oogasalad.model.gameobject.Land;
import oogasalad.model.gameplay.Bag;
import oogasalad.model.gameplay.GameTime;
import oogasalad.model.gameplay.GameWorld;
import oogasalad.model.gameplay.Shop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the state of a particular game save.
 * <p>
 * This includes the current game world (and any modifications made to it), the current time, stats,
 * etc.
 *
 * @author Jason Qiu
 */
public class GameState implements ReadOnlyGameState {

  // TODO: Externalize this to a configuration file.
  // The path to the gamesaves directory from the data directory.
  public static final String GAMESTATE_DIRECTORY_PATH = "gamesaves";
  private GameWorld gameWorld;
  private GameTime gameTime;
  private double energy;
  private int money;
  private Shop shop;
  private Bag bag;
  private int width = 15;
  private int height = 10;
  private int depth = 2;

  /**
   * Creates and returns an instance of {@link GameState} from a JSON file.
   *
   * @param dataFilePath the path to the JSON file.
   * @return the created instance of {@link GameState}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link GameState}
   * @throws IOException          if the filePath could not be opened.
   */
  public static GameState of(String dataFilePath) throws BadGsonLoadException, IOException {
    return FACTORY.load(Paths.get(GAMESTATE_DIRECTORY_PATH, dataFilePath).toString());
  }

  /**
   * Initializes a default GameState.
   */
  public GameState() {
    gameWorld = new GameWorld(height, width, depth);
    gameTime = new GameTime(0,0,0);
    energy = 100;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        gameWorld.setTileGameObject(null, i, j, 0);
      }
    }
  }

  /**
   * Initializes a copy of GameState from the original.
   *
   * @param original the original GameState to copy.
   */
  public GameState(ReadOnlyGameState original) {
    // TODO: IMPLEMENT
  }

  /**
   * Serializes the instance to a JSON file.
   *
   * @param dataFilePath the path to the JSON file with the data directory as the root.
   * @throws IOException if there is an issue writing to the given dataFilePath.
   */
  public void save(String dataFilePath) throws IOException {
    FACTORY.save(Paths.get(GAMESTATE_DIRECTORY_PATH, dataFilePath).toString(), this);
  }

  @Override
  public ReadOnlyGameWorld getGameWorld() {
    return gameWorld;
  }

  @Override
  public ReadOnlyGameTime getGameTime() {
    // TODO: IMPLEMENT
    return null;
  }

  /**
   * Returns the current energy level.
   *
   * @return the current energy level.
   */
  @Override
  public double getEnergy() {
    // TODO: IMPLEMENT
    return 0;
  }

  /**
   * Returns the amount of money currently possessed.
   *
   * @return the amount of money currently possessed.
   */
  @Override
  public int getMoney() {
    // TODO: IMPLEMENT
    return 0;
  }

  /**
   * Returns the current shop, which contains items currently being sold.
   *
   * @return the current shop.
   */
  @Override
  public ReadOnlyShop getShop() {
    // TODO: IMPLEMENT
    return null;
  }

  /**
   * Returns the current bag, which contains items currently held.
   *
   * @return the current bag.
   */
  @Override
  public ReadOnlyBag getBag() {

    return null;
  }

  public GameWorld getEditableGameWorld() {
    // TODO: IMPLEMENT
    return null;
  }

  public GameTime getEditableGameTime() {
    // TODO: IMPLEMENT
    return null;
  }

  private static final DataFactory<GameState> FACTORY = new DataFactory<>(GameState.class);
  private static final Logger LOG = LogManager.getLogger(GameState.class);
}