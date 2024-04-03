package oogasalad.view.playing;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

/**
 * The controller for the playing page
 */

public class PlayingPageController {

  @FXML
  private Label timeLabel;
  private int elapsedTimeSeconds = 0;
  private Timeline timeline;
  private double timeInterval = 1;

  @FXML
  private ProgressBar energyProgressBar;

  @FXML
  private GridPane landGridPane;

  @FXML
  private GridPane toolGridPane;
  @FXML
  private GridPane itemGridPane;

  private ImageView selectedCrop;
  private ImageView selectedTool;
  private double cellWidth;
  private double cellHeight;
  private List<List<GridComponentView>> landGrid;
  private List<List<GridComponentView>> cropGrid;
  private Map<ImageView, GridComponentProperty> cropImagePropertyMap = new HashMap<>();
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
    landGrid = new ArrayList<>();
    cropGrid = new ArrayList<>();
    for (int row = 0; row < numRows; row++) {
      landGrid.add(new ArrayList<>());
      cropGrid.add(new ArrayList<>());
      for (int col = 0; col < numCols; col++) {
        ImageView cell = new ImageView(new Image(
            String.valueOf(new File("src/main/resources/img/rectangle.png").toURI().toURL())));
        cell.setFitWidth(cellWidth);
        cell.setFitHeight(cellHeight);
        cell.setOnMouseClicked(this::handleCellClick);
        List<ImageView> cellAnimation = new ArrayList<>();
        cellAnimation.add(cell);
        landGrid.get(row)
            .add(new GridComponentView(0, new GridComponentProperty(cellAnimation, 0, 0)));
        cropGrid.get(row).add(new GridComponentView(0, null));
      }
    }

    //timeline to update time label every second
    timeline = new Timeline(new KeyFrame(Duration.seconds(timeInterval), event -> {
      landGridPane.getChildren().clear();
      elapsedTimeSeconds += timeInterval;
      updateTimeLabel();
      updateCropGrowth();
      for (int row = 0; row < numRows; row++) {
        for (int col = 0; col < numCols; col++) {
          ImageView landImageView = landGrid.get(row).get(col).getProperty().
              getImageView().get(landGrid.get(row).get(col).getImageViewIndex());
          landGridPane.add(landImageView, col, row);
          if (cropGrid.get(row).get(col).getProperty() != null) {
            // a deep copy, otherwise it raises errors
            ImageView cropImageView = new ImageView(
                cropGrid.get(row).get(col).getProperty().getImageView().
                    get(cropGrid.get(row).get(col).getImageViewIndex()).getImage());
            cropImageView.setFitWidth(cellWidth);
            cropImageView.setFitHeight(cellHeight);
            cropImageView.setOnMouseClicked(this::handleCellClick);
            landGridPane.add(cropImageView, col, row);
          }
        }
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

    ImageView cropImageView0 = new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/blank.png").toURI().toURL())));
    ImageView cropImageView1 = new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/half_panda.png").toURI().toURL())));
    ImageView cropImageView2 = new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/panda.png").toURI().toURL())));
    cropImageView0.setFitWidth(cellWidth);
    cropImageView0.setFitHeight(cellHeight);
    cropImageView1.setFitWidth(cellWidth);
    cropImageView1.setFitHeight(cellHeight);
    cropImageView2.setFitWidth(cellWidth);
    cropImageView2.setFitHeight(cellHeight);
    List<ImageView> cropAnimation = new ArrayList<>();
    cropAnimation.add(cropImageView0);
    cropAnimation.add(cropImageView1);
    cropAnimation.add(cropImageView2);

    ImageView cropImageView = new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/panda.png").toURI().toURL())));
    cropImageView.setFitWidth(toolGridPane.getColumnConstraints().get(0).getPrefWidth());
    cropImageView.setFitHeight(toolGridPane.getRowConstraints().get(0).getPrefHeight());
    cropImagePropertyMap.put(cropImageView, new GridComponentProperty(cropAnimation, 5, 1));

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
    Rectangle chooseBg = new Rectangle(toolGridPane.getColumnConstraints().get(0).getPrefWidth(),
        toolGridPane.getRowConstraints().get(0).getPrefHeight(), Color.web("#0D47A1", 0.5));
    toolGridPane.add(chooseBg,
        GridPane.getColumnIndex(event.getPickResult().getIntersectedNode()),
        GridPane.getRowIndex(event.getPickResult().getIntersectedNode()));
  }

  private void handleCellClick(MouseEvent mouseEvent) {
    int columnIndex = GridPane.getColumnIndex(mouseEvent.getPickResult().getIntersectedNode());
    int rowIndex = GridPane.getRowIndex(mouseEvent.getPickResult().getIntersectedNode());

    if (selectedCrop != null) {

      if (!isCellOccupied(columnIndex, rowIndex)) {
        cropGrid.get(rowIndex).get(columnIndex).setProperty(cropImagePropertyMap.get(selectedCrop));
      }

    } else if (selectedTool != null) {

      if (cropGrid.get(rowIndex).get(columnIndex).getProperty() != null &&
              cropGrid.get(rowIndex).get(columnIndex).getGrowthProgress() >=
                      cropGrid.get(rowIndex).get(columnIndex).getProperty().getGrowthTime() - 1) {
        cropGrid.get(rowIndex).get(columnIndex).setProperty(null);
        cropGrid.get(rowIndex).get(columnIndex).setGrowthProgress(0);
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

  private void updateCropGrowth() {
    for (int row = 0; row < cropGrid.size(); row++) {
      for (int col = 0; col < cropGrid.get(row).size(); col++) {
        if (cropGrid.get(row).get(col).getProperty() != null &&
            cropGrid.get(row).get(col).getGrowthProgress()
                < cropGrid.get(row).get(col).getProperty().getGrowthTime() - 1) {
          cropGrid.get(row).get(col)
              .setGrowthProgress(cropGrid.get(row).get(col).getGrowthProgress() + timeInterval);
        }
      }
    }
  }

  private boolean isCellOccupied(int column, int row) {
    return cropGrid.get(row).get(column).getProperty() != null;
  }
}
