package oogasalad.view.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Objects standing for the land
 */
public class Land {

  private ImageView land;


  private double height;

  private double width;

  public double getHeight() {
    return height;
  }

  public double getWidth() {
    return width;
  }

  public Land(double width, double height, int column, int row, String url) {
    Image image = new Image(url, width, height, false, true);
    land = new ImageView(image);
    this.height = height;
    this.width = width;
  }

  public ImageView getImage() {
    return land;
  }

}
