package oogasalad.model.gameplay;

import java.io.IOException;
import java.util.Optional;
import oogasalad.model.api.GameInterface;
import oogasalad.model.api.ReadOnlyGameConfiguration;
import oogasalad.model.api.ReadOnlyGameState;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyItem;
import oogasalad.model.api.exception.KeyNotFoundException;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.GameState;

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
    state = new GameState();
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
   * @param name the name of the item in the bag to select.
   * @throws KeyNotFoundException if the item is not in the bag.
   */
  @Override
  public void selectItem(String name) throws KeyNotFoundException {
// TODO: IMPLEMENT
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
// TODO: IMPLEMENT
  }

  /**
   * Tries to buy an item from the shop.
   *
   * @param name the name of the item to buy from the shop.
   * @return true if successfully bought, false otherwise.
   * @throws KeyNotFoundException if the item is not in the shop.
   */
  @Override
  public boolean buyItem(String name) throws KeyNotFoundException {
    // TODO: IMPLEMENT
    return false;
  }

  /**
   * Tries to sell an item from the bag.
   *
   * @param name the name of the item to sell from the bag.
   * @throws KeyNotFoundException if the item is not in the bag.
   */
  @Override
  public void sellItem(String name) throws KeyNotFoundException {
    // TODO: IMPLEMENT
  }

  /**
   * Returns true if the game is over, false otherwise.
   *
   * @return true if the game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    // TODO: IMPLEMENT
    return false;
  }

  @Override
  public ReadOnlyGameConfiguration getGameConfiguration() {
    return configuration;
  }

  @Override
  public ReadOnlyGameState getGameState() {
    return state;
  }

  private final GameConfiguration configuration;
  private final GameState state;
}
