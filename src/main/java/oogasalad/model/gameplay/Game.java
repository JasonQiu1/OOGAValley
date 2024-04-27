package oogasalad.model.gameplay;

import java.io.IOException;
import oogasalad.model.api.GameInterface;
import oogasalad.model.api.ReadOnlyGameConfiguration;
import oogasalad.model.api.ReadOnlyGameState;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.model.api.exception.BadValueParseException;
import oogasalad.model.api.exception.KeyNotFoundException;
import oogasalad.model.data.DataFactory;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.gameobject.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of GameInterface.
 * <p>
 * Main driver for all game logic and main liaison for the view.
 *
 * @author Jason Qiu
 */
public class Game implements GameInterface {

  /**
   * Loads a default GameConfiguration that shows off a decent amount of features.
   */
  public Game() {
    configuration = new GameConfiguration();
    try {
      state = GAMESTATE_FACTORY.load("templates/GameState");
    } catch (IOException e) {
      LOG.error("Couldn't load default GameState from 'data/templates/GameState.json'");
      throw new RuntimeException(e);
    }
  }

  /**
   * Loads a new game with an already-loaded configuration.
   *
   * @param configuration the loaded configuration.
   * @throws IOException if the configuration file is not found.
   */
  public Game(GameConfiguration configuration) throws IOException {
    this.configuration = configuration;
    state = new GameState(configuration.getInitialState());
  }

  /**
   * Loads a specific GameConfiguration with the given initial state as the first save.
   *
   * @param configName the name of the config.
   * @throws IOException if the configuration file is not found.
   */
  public Game(String configName) throws IOException {
    configuration = GameConfiguration.of(configName);
    state = new GameState(configuration.getInitialState());
  }

  /**
   * Loads the given config with the given save.
   *
   * @param configName the name of the configuration file in 'data/gameconfigurations'.
   * @param saveName   the name of the save file in 'data/gamesaves'.
   * @throws IOException if the configuration or save file are not found.
   */
  public Game(String configName, String saveName) throws IOException {
    configuration = GameConfiguration.of(configName);
    state = GameState.of(saveName);
  }

  @Override
  public void update() {
    state.getEditableGameTime().update();
    ReadOnlyGameTime currentGameTime = state.getGameTime();
    ReadOnlyGameTime copyOfGameTime =
        new GameTime(currentGameTime.getDay(), currentGameTime.getHour(),
            currentGameTime.getMinute());
    state.getEditableGameWorld().update(copyOfGameTime);
    state.addItemsToBag();
  }

  /**
   * Saves the current GameState to 'data/gamesaves' with the given filename.
   *
   * @param fileName the name of the file to save to.
   * @throws IOException if writing to the file fails.
   */
  @Override
  public void save(String fileName) throws IOException {
    state.save(fileName);
  }

  /**
   * Selects an item in the bag.
   *
   * @param id the name of the item in the bag to select.
   * @throws KeyNotFoundException if the item is not in the bag.
   */
  @Override
  public void selectItem(String id) throws KeyNotFoundException {
    state.selectItem(id);
  }

  /**
   * Interacts with the given coordinate at the world using the selected item.
   *
   * @param x     the interacted x-coordinate.
   * @param y     the interacted y-coordinate.
   * @param depth the interacted depth coordinate.
   */
  @Override
  public void interact(int x, int y, int depth) {
    state.getSelectedItem()
        .ifPresent((ReadOnlyItem item) -> state.getEditableGameWorld().interact(item, x, y, depth));
  }

  /**
   * Restores all energy and passes game time.
   */
  @Override
  public void sleep() {
    try {
      state.getEditableGameTime().advanceTo(configuration.getRules().getInteger("wakeHour"), 0);
    } catch (KeyNotFoundException | BadValueParseException e) {
      LOG.error("Configuration file doesn't contain `wakeHour` key to decide when to wake up after sleeping.");
      throw new RuntimeException(e);
    }
    state.restoreEnergy(Integer.MAX_VALUE);
  }

  /**
   * Tries to buy an item from the shop.
   *
   * @param id the name of the item to buy from the shop.
   * @return true if successfully bought, false otherwise.
   * @throws KeyNotFoundException if the item is not in the shop.
   */
  @Override
  public boolean buyItem(String id) throws KeyNotFoundException {
    double price = getPriceFromShop(id);
    if (price > state.getMoney()) {
      return false;
    }
    state.addMoney((int) -price);
    state.getEditableBag().addItem(id, 1);
    return true;
  }

  /**
   * Tries to sell an item from the bag.
   *
   * @param id the name of the item to sell from the bag.
   * @throws KeyNotFoundException if the item is not in the bag.
   */
  @Override
  public void sellItem(String id) throws KeyNotFoundException {
    Bag bag = state.getEditableBag();
    Item item = new Item(id);
    if (!bag.getItems().containsKey(item)) {
      throw new KeyNotFoundException(id);
    }
    state.addMoney((int) item.getWorth());
    bag.removeItem(id, 1);
  }

  /**
   * Returns true if the game is over, false otherwise.
   * <p>
   * Currently only implements the time goal condition, where the game ends after a certain amount
   * of in-game time.
   *
   * @return true if the game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    return switch (configuration.getRules().getString("goalCondition")) {
      case "time" ->
          state.getGameTime().convertInMinutes() >= configuration.getRules().getInteger("timeGoal");
      case "collect" -> state.getEditableBag()
          .contains(configuration.getRules().getStringIntegerMap("collectGoal"));
      default -> {
        String errorMessage =
            "Unexpected goal condition: " + configuration.getRules().getString("goalCondition");
        LOG.error(errorMessage);
        throw new IllegalStateException(errorMessage);
      }
    };
  }

  @Override
  public ReadOnlyGameConfiguration getGameConfiguration() {
    return configuration;
  }

  @Override
  public ReadOnlyGameState getGameState() {
    return state;
  }

  // Gets the price of an item from the shop.
  private double getPriceFromShop(String id) throws KeyNotFoundException {
    ReadOnlyShop shop = state.getShop();
    Double price = shop.getItems().get(new Item(id));
    if (price == null) {
      throw new KeyNotFoundException(id);
    }
    return price;
  }

  private final GameConfiguration configuration;
  private final GameState state;

  private static final DataFactory<GameState> GAMESTATE_FACTORY =
      new DataFactory<>(GameState.class);

  private static final Logger LOG = LogManager.getLogger(Game.class);
}
