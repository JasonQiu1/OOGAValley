package oogasalad.model.api;

/**
 * Provides read-only access to data in a game state.
 *
 * @author Jason Qiu
 */
public interface ReadOnlyGameState {

  /**
   * Returns the current GameWorld, which can be sent messages to interact with tiles and get the
   * image representation of the world.
   *
   * @return the current GameWorld.
   */
  ReadOnlyGameWorld getGameWorld();

  /**
   * Returns the current GameTime.
   *
   * @return the stored GameTime.
   */
  ReadOnlyGameTime getGameTime();
}
