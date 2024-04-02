package oogasalad.view.playing;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import oogasalad.view.branch.ShoppingPageController;


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
  private GridPane itemGridPane;

  private ImageView selectedCrop;
  private ImageView selectedTool;
  private double cellWidth;
  private double cellHeight;
  private Map<ImageView, Integer> cropGrowthProgressMap = new HashMap<>();

  private Stage stage;

  public void setStage(Stage stage) {
    this.stage = stage;
  }

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
      try {
        updateCropGrowth();
      } catch (MalformedURLException e) {
        throw new RuntimeException(e);
      }
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    //set up tool grid
    Image tool = new Image(
        String.valueOf(new File("src/main/resources/img/tool.png").toURI().toURL()));
    ImageView toolView = new ImageView(tool);
    toolView.setFitWidth(toolGridPane.getColumnConstraints().get(0).getPrefWidth());
    toolView.setFitHeight(toolGridPane.getRowConstraints().get(0).getPrefHeight());
    toolGridPane.add(toolView, 0, 0);

    toolView.setOnMousePressed(event -> {
      selectedTool = (ImageView) event.getSource();
      selectedCrop = null;
      makeElementSelected(event);
    });

    Image cropImage = new Image(
        String.valueOf(new File("src/main/resources/img/panda.png").toURI().toURL()));
    ImageView cropImageView = new ImageView(cropImage);
    cropImageView.setFitWidth(toolGridPane.getColumnConstraints().get(0).getPrefWidth());
    cropImageView.setFitHeight(toolGridPane.getRowConstraints().get(0).getPrefHeight());
    toolGridPane.add(cropImageView, 1, 0);

    cropImageView.setOnMousePressed(event -> {
      selectedCrop = (ImageView) event.getSource();
      selectedTool = null;
      makeElementSelected(event);
    });
  }

  private void makeElementSelected(javafx.scene.input.MouseEvent event) {
    for (Node node : toolGridPane.getChildren()) {
      if (node instanceof Rectangle) {
        toolGridPane.getChildren().remove(node);
        break;
      }
    }
    Rectangle choose_bg = new Rectangle(toolGridPane.getColumnConstraints().get(0).getPrefWidth(),
        toolGridPane.getRowConstraints().get(0).getPrefHeight(), Color.web("#0D47A1", 0.5));
    toolGridPane.add(choose_bg,
        GridPane.getColumnIndex(event.getPickResult().getIntersectedNode()),
        GridPane.getRowIndex(event.getPickResult().getIntersectedNode()));
  }

  private void handleCellClick(MouseEvent mouseEvent) {
    int columnIndex = GridPane.getColumnIndex(mouseEvent.getPickResult().getIntersectedNode());
    int rowIndex = GridPane.getRowIndex(mouseEvent.getPickResult().getIntersectedNode());

    if (selectedCrop != null) {
      ImageView plantedCrop = new ImageView(selectedCrop.getImage());
      plantedCrop.setFitWidth(cellWidth);
      plantedCrop.setFitHeight(cellHeight);
      plantedCrop.setOnMouseClicked(this::handleCellClick);

      if (!isCellOccupied(columnIndex, rowIndex)) {
        cropGrowthProgressMap.put(plantedCrop, 0);
        landGridPane.add(plantedCrop, columnIndex, rowIndex);
      }

    } else if (selectedTool != null) {
      for (Node node : landGridPane.getChildren()) {
        if (GridPane.getColumnIndex(node) == columnIndex && GridPane.getRowIndex(node) == rowIndex
            && node instanceof ImageView && cropGrowthProgressMap.get(node) == 5) {
          landGridPane.getChildren().remove(node);

          break;
        }
      }

    }
  }

  @FXML
  private void openShop() {
    Scene scene = stage.getScene();
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(
          new File("src/main/resources/fxml/shop/shopping_view.fxml").toURI().toURL());
      Parent root = fxmlLoader.load();
      ShoppingPageController shoppingPageController = fxmlLoader.getController();
      shoppingPageController.setStage(stage);
      shoppingPageController.setPreviousScene(scene);
      stage.setScene(new Scene(root));
      stage.show();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void updateTimeLabel() {
    int hours = elapsedTimeSeconds / 3600;
    int minutes = (elapsedTimeSeconds % 3600) / 60;
    int seconds = elapsedTimeSeconds % 60;
    timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
  }

  private void updateCropGrowth() throws MalformedURLException {
    for (ImageView plantedCrop : cropGrowthProgressMap.keySet()) {
      int growthProgress = cropGrowthProgressMap.get(plantedCrop);
      if (growthProgress < 5) {
        growthProgress++;
        cropGrowthProgressMap.put(plantedCrop, growthProgress);
        if (growthProgress < 2) {
          plantedCrop.setImage(null);
        } else if (growthProgress == 3) {
          // Update to show half grown crop after 5 seconds
          plantedCrop.setImage(new Image(
              String.valueOf(new File("src/main/resources/img/half_panda.png").toURI().toURL())));
        } else if (growthProgress == 4) {
          // Update to show fully grown crop after another 5 seconds
          plantedCrop.setImage(new Image(
              String.valueOf(new File("src/main/resources/img/panda.png").toURI().toURL())));
        }
      }
    }
  }

  private boolean isCellOccupied(int column, int row) {
    boolean res = false;
    for (Node node : landGridPane.getChildren()) {
      if (GridPane.getColumnIndex(node) == column &&
          GridPane.getRowIndex(node) == row && node instanceof ImageView) {
        res = true;
      }
    }
    return res;
  }
}
