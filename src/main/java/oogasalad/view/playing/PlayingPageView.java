package oogasalad.view.playing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import oogasalad.view.branch.ShoppingPageView;

/**
 * This class is the view for the playing page. It displays the land grid, tools, and items. It also
 * has a timer and a progress bar for energy. It has a timeline that updates the land grid every
 * second.
 */

public class PlayingPageView extends Application {

  private Stage stage;
  private Label timeLabel = new Label();
  private ProgressBar energyProgressBar = new ProgressBar(0.62);
  private int elapsedTimeSeconds = 0;
  private Timeline timeline;
  private double timeInterval = 1;
  private GridPane landGridPane;
  private GridPane toolGridPane;
  private GridPane itemGridPane;
  private double landCellWidth = 50;
  private double landCellHeight = 50;
  private double bottomCellWidth = 30;
  private double bottomCellHeight = 30;

  private int landNumRows = 10;
  private int landNumCols = 15;
  private int toolNumRows = 2;
  private int toolNumCols = 5;
  private int itemNumRows = 2;
  private int itemNumCols = 5;
  private double leftRightWidth = 50;
  private double leftRightHeight = 300;
  private double topHeight = 50;
  private double topWidth = 800;
  private double bottomHeight = 80;
  private double bottomWidth = 800;
  private double padding = 10;
  private double landGridPaneWidth = landCellWidth * landNumCols;
  private double windowWidth = landGridPaneWidth + leftRightWidth * 2;
  private double landGridPaneHeight = landCellHeight * landNumRows;
  private double windowHeight = landGridPaneHeight + topHeight + bottomHeight + padding * 2;
  private List<List<GridComponentView>> landGrid;
  private List<List<GridComponentView>> cropGrid;
  private Map<ImageView, GridComponentProperty> cropImagePropertyMap = new HashMap<>();

  private ImageView selectedCrop;
  private ImageView selectedTool;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;
    BorderPane root = new BorderPane();
    setupTop(root);
    setupBottom(root);
    setupLeftRight(root);
    setupCenter(root);
    Scene scene = new Scene(root, windowWidth, windowHeight);
    scene.getStylesheets().add("css/playing_css.css");
    primaryStage.setTitle("Playing Mode");
    setUpTimeline();
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void setUpTimeline() {
    timeline = new Timeline(new KeyFrame(Duration.seconds(timeInterval), event -> {
      landGridPane.getChildren().clear();
      elapsedTimeSeconds += timeInterval;
      updateTimeLabel();
      updateCropGrowth();
      for (int row = 0; row < landNumRows; row++) {
        for (int col = 0; col < landNumCols; col++) {
          ImageView landImageView = landGrid.get(row).get(col).getProperty().
              getImageView().get(landGrid.get(row).get(col).getImageViewIndex());
          landGridPane.add(landImageView, col, row);
          if (cropGrid.get(row).get(col).getProperty() != null) {
            // a deep copy, otherwise it raises errors
            ImageView cropImageView = new ImageView(
                cropGrid.get(row).get(col).getProperty().getImageView().
                    get(cropGrid.get(row).get(col).getImageViewIndex()).getImage());
            cropImageView.setFitWidth(landCellWidth);
            cropImageView.setFitHeight(landCellHeight);
            cropImageView.setOnMouseClicked(this::handleCellClick);
            landGridPane.add(cropImageView, col, row);
          }
        }
      }
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void makeElementSelected(javafx.scene.input.MouseEvent event) {
    for (Node node : toolGridPane.getChildren()) {
      if (node instanceof Rectangle) {
        toolGridPane.getChildren().remove(node);
        break;
      }
    }
    Rectangle chooseBg = new Rectangle(bottomCellWidth, bottomCellHeight,
        Color.web("#0D47A1", 0.5));
    toolGridPane.add(chooseBg,
        GridPane.getColumnIndex(event.getPickResult().getIntersectedNode()),
        GridPane.getRowIndex(event.getPickResult().getIntersectedNode()));
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

  private void handleCellClick(MouseEvent mouseEvent) {
    int columnIndex = GridPane.getColumnIndex(mouseEvent.getPickResult().getIntersectedNode());
    int rowIndex = GridPane.getRowIndex(mouseEvent.getPickResult().getIntersectedNode());

    if (selectedCrop != null) {

      if (!isCellOccupied(columnIndex, rowIndex)) {
        cropGrid.get(rowIndex).get(columnIndex).setProperty(cropImagePropertyMap.get(selectedCrop));
      }

    } else if (selectedTool != null) {

      if (cropGrid.get(rowIndex).get(columnIndex).getProperty() != null) {
        if (cropGrid.get(rowIndex).get(columnIndex).getGrowthProgress() >=
            cropGrid.get(rowIndex).get(columnIndex).getProperty().getGrowthTime() - 1) {
          cropGrid.get(rowIndex).get(columnIndex).setProperty(null);
          cropGrid.get(rowIndex).get(columnIndex).setGrowthProgress(0);
        }
      }

    }
  }

  private boolean isCellOccupied(int column, int row) {
    return cropGrid.get(row).get(column).getProperty() != null;
  }

  private void setupTop(BorderPane root) {
    HBox topBox = new HBox();
    topBox.setPrefSize(topWidth, topHeight);
    topBox.getStyleClass().add("top-box");
    Button btnOpenShop = new Button("Shop");
    btnOpenShop.setOnAction(e -> openShop());
    topBox.getChildren().addAll(new Label("Time: "), timeLabel, energyProgressBar, btnOpenShop);
    root.setTop(topBox);
  }

  private void setupCenter(BorderPane root) {
    landGridPane = new GridPane();
    landGridPane.setPrefSize(landGridPaneWidth, landGridPaneHeight);
    landCellWidth = landGridPane.getPrefWidth() / landNumCols - 1;
    landCellHeight = landGridPane.getPrefHeight() / landNumRows - 1;
    landGrid = new ArrayList<>();
    cropGrid = new ArrayList<>();
    for (int row = 0; row < landNumRows; row++) {
      landGrid.add(new ArrayList<>());
      cropGrid.add(new ArrayList<>());
      for (int col = 0; col < landNumCols; col++) {
        ImageView cell = new ImageView(new Image("img/rectangle.png"));
        cell.setFitWidth(landCellWidth);
        cell.setFitHeight(landCellHeight);
        cell.setOnMouseClicked(this::handleCellClick);
        List<ImageView> cellAnimation = new ArrayList<>();
        cellAnimation.add(cell);
        landGrid.get(row)
            .add(new GridComponentView(0, new GridComponentProperty(cellAnimation,
                0, 0)));
        cropGrid.get(row).add(new GridComponentView(0, null));
      }
    }
    landGridPane.getStyleClass().add("land-grid-pane");
    root.setCenter(landGridPane);
  }

  private void setupBottom(BorderPane root) {
    toolGridPane = new GridPane();
    toolGridPane.getStyleClass().add("tool-grid-pane");
    itemGridPane = new GridPane();
    itemGridPane.getStyleClass().add("item-grid-pane");
    Image tool = new Image("img/tool.png");
    ImageView toolView = new ImageView(tool);
    toolView.setFitWidth(bottomCellWidth);
    toolView.setFitHeight(bottomCellHeight);
    toolGridPane.add(toolView, 0, 0);

    toolView.setOnMousePressed(event -> {
      selectedTool = (ImageView) event.getSource();
      selectedCrop = null;
      makeElementSelected(event);
    });

    ImageView cropImageView0 = new ImageView(new Image("img/blank.png"));
    ImageView cropImageView1 = new ImageView(new Image("/img/half_panda.png"));
    ImageView cropImageView2 = new ImageView(new Image("img/panda.png"));
    cropImageView0.setFitWidth(landCellWidth);
    cropImageView0.setFitHeight(landCellHeight);
    cropImageView1.setFitWidth(landCellWidth);
    cropImageView1.setFitHeight(landCellHeight);
    cropImageView2.setFitWidth(landCellWidth);
    cropImageView2.setFitHeight(landCellHeight);
    List<ImageView> cropAnimation = new ArrayList<>();
    cropAnimation.add(cropImageView0);
    cropAnimation.add(cropImageView1);
    cropAnimation.add(cropImageView2);

    ImageView cropImageView = new ImageView(new Image("img/panda.png"));
    cropImageView.setFitWidth(bottomCellWidth);
    cropImageView.setFitHeight(bottomCellHeight);
    cropImagePropertyMap.put(cropImageView,
        new GridComponentProperty(cropAnimation, 5, 1));

    toolGridPane.add(cropImageView, 1, 0);

    cropImageView.setOnMousePressed(event -> {
      selectedCrop = (ImageView) event.getSource();
      selectedTool = null;
      makeElementSelected(event);
    });

    HBox bottomBox = new HBox();
    bottomBox.setPrefSize(bottomWidth, bottomHeight);
    bottomBox.getStyleClass().add("bottom-box");
    bottomBox.getChildren().addAll(toolGridPane, itemGridPane);
    root.setBottom(bottomBox);
  }

  private void setupLeftRight(BorderPane root) {
    VBox leftBox = new VBox();
    leftBox.setPrefSize(leftRightWidth, leftRightHeight);
    leftBox.getStyleClass().add("side-box");
    VBox rightBox = new VBox();
    rightBox.setPrefSize(leftRightWidth, leftRightHeight);
    rightBox.getStyleClass().add("side-box");
    root.setLeft(leftBox);
    root.setRight(rightBox);
  }

  private void openShop() {
    Scene scene = stage.getScene();
    ShoppingPageView shoppingPageView = new ShoppingPageView(stage, scene);
    stage.setScene(new Scene(shoppingPageView.getScene()));
    stage.show();
  }
}
