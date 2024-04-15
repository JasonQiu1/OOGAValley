package oogasalad.model;

/**
 * Interfaces for Game Time object
 */
public interface GameTimeInterface {

  /**
   * This should be called in the game loop in the UI to update the game time.
   */
  void update();

  int getHour();

  int getMinute();

  int getDay();

  /**
   * @param gameTime, another game time object
   * @return the difference between two gameTime objects in minutes
   */
  int getDifferenceInMinutes(GameTimeInterface gameTime);

  int convertInMinutes();


}
