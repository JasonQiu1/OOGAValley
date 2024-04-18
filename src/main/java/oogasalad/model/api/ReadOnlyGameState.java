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

  /**
   * Returns the current energy level.
   *
   * @return the current energy level.
   */
  double getEnergy();

  /**
   * Returns the amount of money currently possessed.
   * @return the amount of money currently possessed.
   */
  int getMoney();

  /**
   * Returns the current shop, which contains items currently being sold.
   *
   * @return the current shop.
   */
  ReadOnlyShop getShop();

  /**
   * Returns the current bag, which contains items currently held.
   *
   * @return the current bag.
   */
  ReadOnlyBag getBag();
}
