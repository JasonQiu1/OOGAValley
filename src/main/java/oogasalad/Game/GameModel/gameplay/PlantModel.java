package oogasalad.Game.GameModel.gameplay;


import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.GameTimeInterface;

public class PlantModel implements PlantModelInterface {

  private GameTime plantedTime;

  private GameTime matureTime;


  /**
   * Initialize a plant model, this contains the information for a single plant
   *
   * @param plantedTime the time this plant is planted
   * @param matureTime  the time this plant will be matured
   */
  public PlantModel(GameTime plantedTime, GameTime matureTime) {
    this.plantedTime = plantedTime;
    this.matureTime = matureTime;
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
}
