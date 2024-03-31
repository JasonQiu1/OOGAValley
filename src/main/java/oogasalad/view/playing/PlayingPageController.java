package oogasalad.view.playing;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class PlayingPageController {

  @FXML
  private Label timeLabel;
  private int elapsedTimeSeconds = 0;
  private Timeline timeline;

  @FXML
  private ProgressBar energyProgressBar;

  @FXML
  private GridPane landGridPane;

  @FXML
  private GridPane toolGridPane;

  private ImageView selectedCrop;
  private double cellWidth;
  private double cellHeight;


  @FXML
  private void initialize() throws MalformedURLException {
    // Set up land grid
    int numRows = 10;
    int numCols = 15;
    cellWidth = landGridPane.getPrefWidth() / numCols - 1;
    cellHeight = landGridPane.getPrefHeight() / numRows - 1;

    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        Rectangle cell = new Rectangle(cellWidth, cellHeight, Color.WHITE);
        cell.setOnMouseClicked(this::handleCellClick);
        landGridPane.add(cell, col, row);
      }
    }

    //timeline to update time label every second
    timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
      elapsedTimeSeconds++;
      updateTimeLabel();
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

    //set up tool grid
    Image tool = new Image(
        String.valueOf(new File("src/main/resources/img/tool.png").toURI().toURL()));
    ImageView toolView = new ImageView(tool);
    toolView.setFitWidth(toolGridPane.getColumnConstraints().get(0).getPrefWidth());
    toolView.setFitHeight(toolGridPane.getRowConstraints().get(0).getPrefHeight());
    toolGridPane.add(toolView, 0, 0);

    Image cropImage = new Image(
        String.valueOf(new File("src/main/resources/img/panda.png").toURI().toURL()));
    ImageView cropImageView = new ImageView(cropImage);
    cropImageView.setFitWidth(toolGridPane.getColumnConstraints().get(0).getPrefWidth());
    cropImageView.setFitHeight(toolGridPane.getRowConstraints().get(0).getPrefHeight());
    toolGridPane.add(cropImageView, 1, 0);

    cropImageView.setOnMousePressed(event -> {
      selectedCrop = (ImageView) event.getSource();
      System.out.println(toolGridPane.getChildren().get(1));
      toolGridPane.getChildren().get(1).setStyle("-fx-border-color: red; -fx-border-width: 2px");
    });

  }

  private void handleCellClick(javafx.scene.input.MouseEvent mouseEvent) {
    if (selectedCrop != null) {
      ImageView plantedCrop = new ImageView(selectedCrop.getImage());
      plantedCrop.setFitWidth(cellWidth);
      plantedCrop.setFitHeight(cellHeight);
      landGridPane.add(plantedCrop, GridPane.getColumnIndex(mouseEvent.getPickResult().getIntersectedNode()), GridPane.getRowIndex(mouseEvent.getPickResult().getIntersectedNode()));
    }
  }


  @FXML
  private void openShop() {
    System.out.println("Opening shop...");
  }

  private void updateTimeLabel() {
    int hours = elapsedTimeSeconds / 3600;
    int minutes = (elapsedTimeSeconds % 3600) / 60;
    int seconds = elapsedTimeSeconds % 60;
    timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
  }

  private Pane getCellPane(GridPane gridPane, ImageView imageView) {
    for (int i = 0; i < gridPane.getChildren().size(); i++) {
      Pane cellPane = (Pane) gridPane.getChildren().get(i);
      if (cellPane.getChildren().contains(imageView)) {
        return cellPane;
      }
    }
    return null;
  }
}
