package oogasalad.view.item;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import oogasalad.Game.GameModel.GameTimeInterface;
import oogasalad.Game.GameModel.gameplay.PlantModel;

public class PlantView {

  private PlantModel plantModel;

  private final boolean DEBUG = true;

  // the progress bar for debug usage
  private ProgressBar progressBar;

  private ImageView imageView;

  private StackPane stackPane;

  private final double height;

  private final double width;

  private String imagePath = "";

  /**
   * Initialize the object
   *
   * @param plantModel the plant model
   * @param height     the height the plant will take
   * @param width      the width the plant will take
   */
  public PlantView(PlantModel plantModel, double height, double width) {
    this.plantModel = plantModel;
    this.height = height;
    this.width = width;
    imageView = new ImageView();
    stackPane = new StackPane();
    stackPane.getChildren().add(imageView);
    if (DEBUG) {
      progressBar = new ProgressBar();
      progressBar.setProgress(0.0);
      progressBar.setMaxWidth(width);
      stackPane.getChildren().add(progressBar);
    }
  }


  /**
   * @return update the image
   */
  public void updateImage(GameTimeInterface gameTime) {
    String statusImagePath = plantModel.getStatusImagePath(gameTime);
    if (DEBUG) {
      progressBar.setProgress(plantModel.getProgress(gameTime));
    }
    if (imagePath.equals(statusImagePath)) {
      return;
    }
    imagePath = statusImagePath;
    Image image = new Image(statusImagePath, width, height, true, true);
    imageView.setImage(image);

  }

  public StackPane getView() {
    return stackPane;
  }
  public double getProgress(GameTimeInterface gameTime) {
    return plantModel.getProgress(gameTime);
  }

  public String getToolUrl() {
    return imagePath;
  }
}
