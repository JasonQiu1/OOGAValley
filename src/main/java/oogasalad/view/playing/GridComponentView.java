package oogasalad.view.playing;

/**
 * This class is used to store the properties of a grid component
 */

public class GridComponentView {

  private double growthProgress;
  private GridComponentProperty property;

  public GridComponentView(double growthProgress, GridComponentProperty property) {
    this.growthProgress = growthProgress;
    this.property = property;
  }

  public double getGrowthProgress() {
    return growthProgress;
  }

  public void setGrowthProgress(double growthProgress) {
    this.growthProgress = growthProgress;
  }

  public GridComponentProperty getProperty() {
    return property;
  }

  public void setProperty(GridComponentProperty property) {
    this.property = property;
  }

  public int getImageViewIndex() {
    if (property.getGrowthTime() == 0) {
      return 0;
    }
    double interval = property.getGrowthTime() / property.getImageView().size();
    return (int) (growthProgress / interval);
  }
}
