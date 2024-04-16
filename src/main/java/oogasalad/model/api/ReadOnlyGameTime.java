package oogasalad.model.api;

import oogasalad.model.gameplay.GameTimeInterface;

/**
 * Provides methods for accessing parts of the GameTime and perform arithmetic without being able to
 * modify it.
 *
 * @author Beilong Tang, Jason Qiu
 */
public interface ReadOnlyGameTime {

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
