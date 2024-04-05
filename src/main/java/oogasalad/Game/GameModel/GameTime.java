package oogasalad.Game.GameModel;


import java.time.Duration;
import java.time.Instant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameTime implements GameTimeInterface {


  // an hour of the game equals 43 seconds in real-time, (This is the default setting in Stardew valley).
  // this field stands for the real-time on behalf of the 10 minutes of game time
  // in other words, (unit) minute game time equals rate (in milliseconds)
  // by default, the unit is 10.
  private static final double rate = 43000.0 / 6.0;
  private static final int unit = 10;

  private static final Logger LOG = LogManager.getLogger(GameTime.class);

  private Instant previous;

  private long accumulate;

  private int day;

  private int hour;

  private int minute;

  private void updateTime() {
    minute += unit;
    if (minute >= 60) {
      minute = 0;
      hour += 1;
    }
    if (hour >= 24) {
      hour = 0;
      day += 1;
    }
  }

  /**
   * @param day    the day
   * @param hour   the hour
   * @param minute the minute
   */
  public GameTime(int day, int hour, int minute) {
    this.day = day;
    this.hour = hour;
    this.minute = minute;
  }

  /**
   * This should be called in the game loop in the UI to update the game time.
   */
  @Override
  public void update() {
    if (previous == null) {
      previous = Instant.now();
    }
    Instant now = Instant.now();
    long timeElapsedMillis = Duration.between(previous, now).toMillis();
    previous = now;
    accumulate += timeElapsedMillis;
    if (accumulate >= rate) {
      accumulate = 0;
      updateTime();
    }
  }

  @Override
  public int getHour() {
    return hour;
  }

  @Override
  public int getMinute() {
    return minute;
  }

  @Override
  public int getDay() {
    return day;
  }

  /**
   * @param gameTime@return the difference between two gameTime objects in minutes
   * @return the (gameTime - this time)
   */
  @Override
  public int getDifferenceInMinutes(GameTimeInterface gameTime) {
    int totalMinute2 = gameTime.getMinute() + gameTime.getHour() * 60 + gameTime.getDay() * 24 * 60;
    int totalMinute1 = getMinute() + getHour() * 60 + getDay() * 24 * 60;
    return totalMinute2 - totalMinute1;
  }

  @Override
  public String toString() {
    return String.format("Day: %d; Hour: %d, Minute: %d", day, hour, minute);
  }
}
