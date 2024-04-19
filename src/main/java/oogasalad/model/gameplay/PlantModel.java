package oogasalad.model.gameplay;


import oogasalad.model.api.PlantModelInterface;

/**
 * @deprecated SLATED TO GET DESTROYED
 */
public class PlantModel implements PlantModelInterface {

  private final GameTime plantedTime;

  private final GameTime matureTime;

  private final String[] statusImagePath;
  private final String toolUrl;
  private final String itemUrl;

  private final int x;
  private final int y;


  /**
   * Initialize the model using builder
   *
   * @param pb the plant builder class
   */
  public PlantModel(Builder pb) {
    this.plantedTime = pb.plantedTime;
    this.matureTime = pb.matureTime;
    this.statusImagePath = pb.statusImagePath;
    assert statusImagePath.length >= 1;
    this.toolUrl = pb.toolUrl;
    this.itemUrl = pb.itemUrl;
    this.x = pb.x;
    this.y = pb.y;
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

  /**
   * Builder class to avoid long list of arguments, see pattern here
   * https://www.digitalocean.com/community/tutorials/builder-design-pattern-in-java
   */
  public static class Builder {

    private GameTime plantedTime;

    private GameTime matureTime;

    private String[] statusImagePath;
    private String toolUrl;
    private String itemUrl;

    private int x;
    private int y;

    public Builder setX(int x) {
      this.x = x;
      return this;
    }

    public Builder setItemUrl(String itemUrl) {
      this.itemUrl = itemUrl;
      return this;
    }

    public Builder setMatureTime(GameTime matureTime) {
      this.matureTime = matureTime;
      return this;
    }

    public Builder setPlantedTime(GameTime plantedTime) {
      this.plantedTime = plantedTime;
      return this;
    }

    public Builder setStatusImagePath(String[] statusImagePath) {
      this.statusImagePath = statusImagePath.clone();
      return this;
    }

    public Builder setToolUrl(String toolUrl) {
      this.toolUrl = toolUrl;
      return this;
    }

    public Builder setY(int y) {
      this.y = y;
      return this;
    }

    public PlantModel build() {
      return new PlantModel(this);
    }
  }

}
