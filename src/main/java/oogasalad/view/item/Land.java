package oogasalad.view.item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Objects standing for the land.
 */
public class Land {

  private final ImageView land;

  private final double height;

  private final double width;
  private final int column;
  private final int row;

  /**
   * Constructor for the Land class.
   *
   * @param width  the width of the land
   * @param height the height of the land
   * @param column the column of the land
   * @param row    the row of the land
   * @param url    the url of the land
   */
  public Land(double width, double height, int column, int row, String url) {
    Image image = new Image(url, width, height, false, true);
    land = new ImageView(image);
    this.height = height;
    this.width = width;
    this.column = column;
    this.row = row;
  }

  public double getHeight() {
    return height;
  }

  public double getWidth() {
    return width;
  }

  public ImageView getImage() {
    return land;
  }

}
