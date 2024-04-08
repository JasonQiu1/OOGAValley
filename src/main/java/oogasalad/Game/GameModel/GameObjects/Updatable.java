package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;

/**
 * Defines an entity within the game that can be updated over time. Implementing this interface
 * allows an object to participate in the game's loop, responding to changes in the game state
 * or the passage of time.
 * <p>
 * Entities implementing this interface typically represent dynamic aspects of the game,
 * such as land, structures, or any interactive elements that change state or behavior over time.
 */
public interface Updatable {

  /**
   * Updates the state of the object based on the current game time. This method is intended
   * to be called on each game loop iteration, allowing the object to perform time-sensitive actions,
   * such as moving, reacting to game events, or updating internal state.
   *
   * @param gameTime The current game time, providing context for time-based updates.
   */
  void update(GameTime gameTime);

  /**
   * Retrieves the current state of the object. The specific meaning of the state value is
   * defined by the implementing class. This could represent different stages in an object's
   * lifecycle, various conditions it might be in, or other stateful distinctions relevant to
   * the game's logic.
   *
   * @return An integer representing the current state of the object.
   */
  int getState();

}

