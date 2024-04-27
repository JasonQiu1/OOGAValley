package oogasalad.model.gameplay;

import java.io.IOException;
import oogasalad.model.api.GameInterface;
import oogasalad.model.api.ReadOnlyGameConfiguration;
import oogasalad.model.api.ReadOnlyGameState;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.api.ReadOnlyShop;
import oogasalad.model.api.exception.KeyNotFoundException;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.gameobject.Item;

/**
 * Implementation of GameInterface.
 * <p>
 * Main driver for all game logic and main liaison for the view.
 *
 * @author Jason Qiu
 */
public class Game implements GameInterface {

  public Game() {
    configuration = new GameConfiguration();
    state = new GameState(configuration.getRules());
  }

  public Game(String configName) throws IOException {
    configuration = GameConfiguration.of(configName);
    state = new GameState(configuration.getInitialState());
  }

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
    // TODO: EXTERNALIZE AMOUNT OF TIME SLEPT OR TIME TO SLEEP TO
    // Currently advances to 6 AM by default.
    state.getEditableGameTime().advanceTo(6, 0);
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
    return state.getGameTime().convertInMinutes() >= configuration.getRules()
        .getInteger("timeGoal");
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
}
