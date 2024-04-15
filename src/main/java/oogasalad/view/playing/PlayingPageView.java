package oogasalad.view.playing;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import oogasalad.model.GameTime;
import oogasalad.model.gameplay.PlantModel;
import oogasalad.model.shop.Bag;
import oogasalad.model.shop.Shop;
import oogasalad.view.item.BagItemView;
import oogasalad.view.item.LandView;
import oogasalad.view.item.SelectedItem;
import oogasalad.view.item.Tool;
import oogasalad.view.item.ToolView;
import oogasalad.view.item.TopAnimationView;
import oogasalad.view.shopping.ShoppingView;

/**
 * This class is the view for the playing page. It displays the land grid, tools, and items. It also
 * has a timer and a progress bar for energy. It has a timeline that updates the land grid every
 * second.
 */

public class PlayingPageView {

  public static final double landCellWidth = 50;
  public static final double landCellHeight = 50;
  public static final double bottomCellWidth = 30;
  public static final double bottomCellHeight = 30;
  public static final double bottomBoxWidth = 300;
  public static final double bottomBoxHeight = 80;
  public static final int landNumRows = 10;
  public static final int landNumCols = 15;
  public static final double topHeight = 50;
  public static final double topWidth = 800;
  public static final double bottomHeight = 100;
  public static final double bottomWidth = 800;
  public static final double padding = 10;
  public static final double leftRightWidth = 50;
  public static final double landGridPaneWidth = landCellWidth * landNumCols;

  public static final double windowWidth = landGridPaneWidth + leftRightWidth * 2 - padding * 2;
  public static final double landGridPaneHeight = landCellHeight * landNumRows;
  public static final double windowHeight =
      landGridPaneHeight + topHeight + bottomHeight;
  public static final double leftRightHeight = 300;
  public static final double bottomBoxPadding = 50;
  private final Label timeLabel = new Label();
  private final ProgressBar energyProgressBar = new ProgressBar(0.62);
  private final GameTime gameTime = new GameTime(1, 8, 0);
  private final String selectedTools = "plant";
  private final SelectedItem selectedItem = new SelectedItem();
  private Stage stage;
  private LandView landView;
  private ToolView toolView;
  private BagItemView bagItemView;
  private TopAnimationView topAnimationView;
  private Bag bag = new Bag();
  private Shop shop = new Shop();

  public PlayingPageView(Stage primaryStage) {
    stage = primaryStage;
  }

  public void start() {
    StackPane root = new StackPane();
    root.getStyleClass().add("playing-root");
    BorderPane borderPane = new BorderPane();
    initModel();
    setupTop(borderPane);
    setupLeftRight(borderPane);
    setupCenter(borderPane);
    setupBottom(borderPane);
    root.getChildren().addAll(borderPane, topAnimationView);
    StackPane.setAlignment(borderPane, javafx.geometry.Pos.TOP_LEFT);
    StackPane.setAlignment(topAnimationView, javafx.geometry.Pos.TOP_LEFT);
    Scene scene = new Scene(root, windowWidth, windowHeight);
    scene.getStylesheets().add("styles.css");
    scene.setOnMouseClicked(event -> {
    });
    stage.setTitle("Playing Mode");
    setUpdate();
    stage.setScene(scene);
    stage.show();
  }

  private void initModel() {

    Tool tool1 = new Tool("img/tool.png", bottomCellHeight, bottomCellWidth, selectedItem);
    Tool tool2 = new Tool("img/panda.png", bottomCellHeight, bottomCellWidth, selectedItem);
    List<Tool> tools = new ArrayList<>();
    tools.add(tool1);
    tools.add(tool2);
    toolView = new ToolView(tools, 5, 1);

    bagItemView = new BagItemView(5, 1, bag);
    topAnimationView = new TopAnimationView(bagItemView, windowWidth, windowHeight);

    List<PlantModel> plantModelList = new ArrayList<>();
    landView = new LandView(plantModelList, gameTime, selectedItem, bagItemView, topAnimationView);
  }

  private void setUpdate() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), event -> {
      gameTime.update();
      updateTimeLabel();
      landView.update(gameTime);
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void updateTimeLabel() {
    timeLabel.setText("Time: " + gameTime);
  }


  private void setupTop(BorderPane root) {
    HBox topBox = new HBox();
    topBox.setPrefSize(topWidth, topHeight);
    topBox.getStyleClass().add("top-box");
    Button btnOpenShop = new Button();
    btnOpenShop.setId("shopButton");
    btnOpenShop.setOnAction(e -> openShop());
    timeLabel.getStyleClass().add("play-top-label");
    topBox.getChildren().addAll(timeLabel, energyProgressBar, btnOpenShop);
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
    StackPane toolStackPane = toolView.getToolStackPane();
    StackPane itemStackPane = bagItemView.getItemStackPane();
    HBox.setMargin(itemStackPane, new javafx.geometry.Insets(0, 0, 0, bottomBoxPadding));
    bottomBox.getChildren().addAll(toolStackPane, itemStackPane);
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
    ShoppingView shoppingPageView = new ShoppingView(shop, bag, stage, scene);
    Scene shoppingScene = new Scene(shoppingPageView.getScene());
    shoppingScene.getStylesheets().add("styles.css");
    stage.setScene(shoppingScene);
    stage.show();
  }
}
