package oogasalad.Game.GameModel.gameplay;


import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.GameTimeInterface;

public class PlantModel implements PlantModelInterface {

  private GameTime plantedTime;

  private GameTime matureTime;

  private String[] statusImagePath;
  private String toolUrl;
  private String itemUrl;

  private int x;
  private int y;

  /**
   * Initialize a plant model, this contains the information for a single plant
   *
   * @param plantedTime     the time this plant is planted
   * @param matureTime      the time this plant will be matured
   * @param statusImagePath a list of images that the view will use to show
   */
  public PlantModel(GameTime plantedTime, GameTime matureTime, String[] statusImagePath,
      String toolUrl, String itemUrl, int x,
      int y) {
    this.plantedTime = plantedTime;
    this.matureTime = matureTime;
    this.statusImagePath = statusImagePath.clone();
    assert statusImagePath.length >= 1;
    this.toolUrl = toolUrl;
    this.itemUrl = itemUrl;
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  /**
   * Get the percentage of the growing till it matures
   *
   * @param gameTime the current game time
   * @return double between [0, 1] (inclusive both side)
   */
  @Override
  public double getProgress(GameTimeInterface gameTime) {
    if (plantedTime.getDifferenceInMinutes(gameTime) <= 0) {
      return 0.0;
    }
    double ratio =
        plantedTime.getDifferenceInMinutes(gameTime) / ((double) matureTime.convertInMinutes());
    return (ratio > 1) ? 1.0 : ratio;
  }

  /**
   * @param gameTime the current game time
   * @return a string standing for the url for the image corresponding to its current status
   * (progress)
   */
  @Override
  public String getStatusImagePath(GameTimeInterface gameTime) {
    double progress = getProgress(gameTime);
    if (statusImagePath.length == 1) {
      return statusImagePath[0];
    }
    int index = 0;
    while ((index + 1) * (1.0 / (statusImagePath.length - 1)) <= progress) {
      index++;
    }
    return statusImagePath[index];
  }

  public String getToolUrl() {
    return toolUrl;
  }

  public String getItemUrl() {
    return itemUrl;
  }
}
