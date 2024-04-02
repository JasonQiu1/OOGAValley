package oogasalad.view.playing;
/**
 * This class is used to store the properties of a grid component
 */

import java.util.List;
import javafx.scene.image.ImageView;

public class GridComponentProperty {

  private List<ImageView> imageView;
  private double growthTime;
  private int toolRequired;

  public GridComponentProperty(List<ImageView> imageView, double growthTime, int toolRequired) {
    this.imageView = imageView;
    this.growthTime = growthTime;
    this.toolRequired = toolRequired;
  }

  public List<ImageView> getImageView() {
    return imageView;
  }

  public void setImageView(List<ImageView> imageView) {
    this.imageView = imageView;
  }

  public double getGrowthTime() {
    return growthTime;
  }

  public void setGrowthTime(double growthTime) {
    this.growthTime = growthTime;
  }

  public int getToolRequired() {
    return toolRequired;
  }

  public void setToolRequired(int toolRequired) {
    this.toolRequired = toolRequired;
  }

}
