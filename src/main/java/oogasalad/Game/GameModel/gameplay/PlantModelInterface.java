package oogasalad.Game.GameModel.gameplay;


import oogasalad.Game.GameModel.GameTimeInterface;

/**
 * The structures on the land, like plants,
 */
public interface PlantModelInterface {


  /**
   * Get the percentage of the growing till it matures
   *
   * @param gameTime
   * @return
   */
  double getProgress(GameTimeInterface gameTime);


}
