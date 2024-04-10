package oogasalad.Game.GameModel.GameObjects;

/**
 * Defines the contract for game objects that have an expirable nature within the game's environment.
 * Implementing this interface allows objects to undergo a lifecycle that culminates in an expired state,
 * potentially leading to their transformation, removal, or triggering specific game events. The notion of
 * expiring could be tied to time, player actions, or other game mechanics.
 */
public interface Expirable {

  /**
   * Determines whether this object has reached its expired state. The criteria for expiration can vary,
   * including but not limited to, time elapsed, interactions received, or other conditions defined within
   * the game's logic.
   *
   * @return {@code true} if the object is in its expired state; {@code false} otherwise.
   */
  boolean isExpired();

}

