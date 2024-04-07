package oogasalad.view.playing;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.gameplay.PlantModel;
import oogasalad.view.branch.ShoppingPageView;
import oogasalad.view.item.ItemView;
import oogasalad.view.item.LandView;
import oogasalad.view.item.SelectedItem;
import oogasalad.view.item.Tool;
import oogasalad.view.item.ToolView;

/**
 * This class is the view for the playing page. It displays the land grid, tools, and items. It also
 * has a timer and a progress bar for energy. It has a timeline that updates the land grid every
 * second.
 */

public class PlayingPageView extends Application {

  private Stage stage;
  private Label timeLabel = new Label();
  private ProgressBar energyProgressBar = new ProgressBar(0.62);
  private Timeline timeline;
  private LandView landView;
  private ToolView toolView;
  private ItemView itemView;

  private GameTime gameTime = new GameTime(1, 8, 0);
  public static final double landCellWidth = 50;
  public static final double landCellHeight = 50;
  public static final double bottomCellWidth = 30;
  public static final double bottomCellHeight = 30;
  public static final int landNumRows = 10;
  public static final int landNumCols = 15;
  private static final double leftRightWidth = 50;
  private static final double leftRightHeight = 300;
  public static final double topHeight = 50;
  public static final double topWidth = 800;
  public static final double bottomHeight = 80;
  public static final double bottomWidth = 800;
  public static final double padding = 10;
  public static final double landGridPaneWidth = landCellWidth * landNumCols;
  public static final double windowWidth = landGridPaneWidth + leftRightWidth * 2 - padding * 2;
  public static final double landGridPaneHeight = landCellHeight * landNumRows;
  public static final double windowHeight =
      landGridPaneHeight + topHeight + bottomHeight;
  private List<PlantModel> plantModelList;

  private String selectedTools = "plant";

  private SelectedItem selectedItem = new SelectedItem();

  @Override
  public void start(Stage primaryStage) {
    stage = primaryStage;
    BorderPane root = new BorderPane();
    initModel();
    setupTop(root);
    setupLeftRight(root);
    setupCenter(root);
    setupBottom(root);
    Scene scene = new Scene(root, windowWidth, windowHeight);
    scene.getStylesheets().add("css/playing_css.css");
    scene.setOnMouseClicked(event -> {
    });
    primaryStage.setTitle("Playing Mode");
    setUpdate();
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void initModel() {
    toolView = new ToolView();
    Tool tool1 = new Tool("img/tool.png", bottomCellHeight, bottomCellWidth, selectedItem,
        toolView);
    Tool tool2 = new Tool("img/panda.png", bottomCellHeight, bottomCellWidth, selectedItem,
        toolView);
    String[] imagePath = {"/img/half_panda.png", "/img/panda.png"};
    itemView = new ItemView();

    plantModelList = new ArrayList<>();
    plantModelList.add(new PlantModel(new GameTime(1, 2, 0),
        new GameTime(0, 6, 30), imagePath, "img/tool.png", 0, 0));
    plantModelList.add(new PlantModel(new GameTime(1, 2, 0),
        new GameTime(0, 15, 30), imagePath, "img/tool.png", 1, 0));
    landView = new LandView(plantModelList, gameTime, selectedItem);
  }

  private void setUpdate() {
    timeline = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), event -> {
      gameTime.update();
      updateTimeLabel();
      landView.update(gameTime);
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void updateTimeLabel() {
    timeLabel.setText(gameTime.toString());
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

    root.setCenter(landView.getGridView());
  }

  private void setupBottom(BorderPane root) {
    HBox bottomBox = new HBox();
    bottomBox.setPadding(new Insets(padding));
    bottomBox.setPrefSize(bottomWidth, bottomHeight);
    bottomBox.getStyleClass().add("bottom-box");
    GridPane toolGridPane = toolView.getToolGridPane();
    GridPane itemGridPane = itemView.getItemGridPane();
    toolGridPane.setStyle("-fx-background-color: lightgray;"+"-fx-padding: 10;");
    itemGridPane.setStyle("-fx-background-color: lightgray;");
    HBox.setMargin(itemGridPane, new javafx.geometry.Insets(0, 0, 0, 50));
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
