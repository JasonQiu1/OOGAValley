package oogasalad.model.api;

import java.io.IOException;
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
    state.getEditableGameWorld().update(state.getGameTime());
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
