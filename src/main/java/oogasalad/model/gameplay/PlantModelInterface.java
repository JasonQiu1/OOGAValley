package oogasalad.model.gameplay;


import oogasalad.model.api.GameTimeInterface;

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

  /**
   * @return a list of strings standing for a list of images to be shown as the stage as the plant
   * grows
   */
  String getStatusImagePath(GameTimeInterface gameTime);

}
