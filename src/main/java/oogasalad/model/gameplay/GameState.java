package oogasalad.model.gameplay;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import oogasalad.model.api.ReadOnlyBag;
import oogasalad.model.api.ReadOnlyGameState;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyGameWorld;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.model.api.exception.BadGsonLoadException;
import oogasalad.model.api.exception.KeyNotFoundException;
import oogasalad.model.data.DataFactory;
import oogasalad.model.gameobject.Item;
import oogasalad.view.playing.PlayingPageView;
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
  private static final DataFactory<GameState> FACTORY = new DataFactory<>(GameState.class);
  private static final Logger LOG = LogManager.getLogger(GameState.class);
  private BuildableTileMap gameWorld;
  private GameTime gameTime;
  private ReadOnlyItem selectedItem;
  private double energy;
  private int money;
  private Shop shop;
  private Bag bag;
  private final int width = 15;
  private final int height = 10;
  private final int depth = 2;

  /**
   * Initializes a default GameState.
   */
  public GameState(ReadOnlyProperties properties) {
    this.bag = new Bag();
    this.gameWorld = new BuildableTileMap(PlayingPageView.landNumRows, PlayingPageView.landNumCols, 1);
    this.gameTime = new GameTime(1, 8, 0);
    try {
      List<String> possibleItemStrings = properties.getStringList("shopPossibleItems");
      List<ReadOnlyItem> possibleItems = new ArrayList<>();
      for (String id : possibleItemStrings) {
        possibleItems.add(new Item(id));
      }
      this.shop = new Shop(gameTime, possibleItems, properties.getInteger("shopRotationSize"),
          properties.getInteger("shopRotationTime"));
    } catch (KeyNotFoundException e) {
      LOG.error("Couldn't load the shop!");
      throw new RuntimeException(e);
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

  public BuildableTileMap getEditableMap() {return gameWorld;}

  @Override
  public ReadOnlyGameTime getGameTime() {
    return gameTime;
  }

  /**
   * Returns the current energy level.
   *
   * @return the current energy level.
   */
  @Override
  public double getEnergy() {
    return energy;
  }

  /**
   * Returns the amount of money currently possessed.
   *
   * @return the amount of money currently possessed.
   */
  @Override
  public int getMoney() {
    return money;
  }

  /**
   * Returns the current shop, which contains items currently being sold.
   *
   * @return the current shop.
   */
  @Override
  public ReadOnlyShop getShop() {
    return shop;
  }

  /**
   * Add items from GameWorld to the player's bag.
   */
  public void addItemsToBag() {
    bag.addItems(gameWorld.itemsToAddToInventory());
  }

  /**
   * Returns the current bag, which contains items currently held.
   *
   * @return the current bag.
   */
  @Override
  public ReadOnlyBag getBag() {
    return bag;
  }

  /**
   * Returns the selected item, if there is one selected.
   *
   * @return the optional describing the selected item.
   */
  @Override
  public Optional<ReadOnlyItem> getSelectedItem() {
    return Optional.of(selectedItem);
  }

  /**
   * Selects an item to be active if it is in the bag.
   *
   * @param id
   */
  public void selectItem(String id) throws KeyNotFoundException {
    Item item = new Item(id);
    if (!bag.getItems().containsKey(item)) {
      throw new KeyNotFoundException(id);
    }
    selectedItem = item;
  }

  /**
   * Add/subtract from the current amount of money.
   *
   * @param amount amount to add/subtract from the current amount of money.
   */
  public void addMoney(int amount) {
    money += amount;
  }

  public GameWorld getEditableGameWorld() {
    return gameWorld;
  }

  public GameTime getEditableGameTime() {
    return gameTime;
  }

  public Bag getEditableBag() {
    return bag;
  }

  /**
   * Restores energy by the given amount up to the max amount.
   *
   * @return the amount of energy restored.
   */
  public double restoreEnergy(double amount) {
    // TODO: IMPLEMENT
    return 0.0f;
  }

}