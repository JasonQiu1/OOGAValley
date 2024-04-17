package oogasalad.model.api;

/**
 * Main API for the view to interact with.
 * <p>
 * Provides functions to interact with the world and get data about the current state,
 * configuration, and configurables.
 *
 * @author Jason Qiu
 */
public interface GameInterface {

  /**
   * Updates the game model since the last time it was updated in realtime.
   * <p>
   * The implementation will independently keep track of time passed, so no delta time parameter is
   * needed.
   */
  void update();

  /**
   * Returns the currently loaded GameConfiguration, which provides methods to get the currently
   * configured rules, store of configurables, initial GameState, etc.
   *
   * @return the currently loaded GameConfiguration.
   */
  ReadOnlyGameConfiguration getGameConfiguration();

  /**
   * Returns the current GameState, from which GameTime, GameWorld, and other stateful information
   * can be retrieved.
   *
   * @return the current GameState instance.
   */
  ReadOnlyGameState getGameState();
}
