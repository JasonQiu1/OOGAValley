package oogasalad.view.playing;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import oogasalad.model.gameplay.GameTime;
import oogasalad.model.gameplay.PlantModel;
import oogasalad.model.shop.Bag;
import oogasalad.model.shop.Shop;

import oogasalad.view.playing.component.BagItem;
import oogasalad.view.playing.component.BagView;
import oogasalad.view.playing.component.LandView;
import oogasalad.view.playing.component.Money;
import oogasalad.view.playing.component.SelectedItem;
import oogasalad.view.playing.component.TopAnimationView;

import oogasalad.view.shopping.ShoppingView;
import oogasalad.view.shopping.components.top.CurrentMoneyHbox;

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
  private static final String DEFAULT_RESOURCE_PACKAGE = "view.playing.";
  private final Label timeLabel = new Label();
  private final ProgressBar energyProgressBar = new ProgressBar(0.62);
  private final GameTime gameTime = new GameTime(1, 8, 0);
  private final String selectedTools = "plant";
  private final SelectedItem selectedItem = new SelectedItem();
  private final Stage stage;
  private LandView landView;
  private TopAnimationView topAnimationView;

  private Money money = new Money(100);
  private final Bag bag = new Bag();
  private final Shop shop = new Shop(money);

  private String myLanguage = "EnglishDisplayText";
  private ResourceBundle displayTextResource;
  private BagView bagView;


  public PlayingPageView(Stage primaryStage) {
    stage = primaryStage;
  }

  public void start() {
    displayTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);

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
    stage.setTitle(displayTextResource.getString("play_title"));
    ;
    setUpdate();
    stage.setScene(scene);
    stage.show();
  }

  private void initModel() {

    BagItem bagItem1 = new BagItem("img/tool.png", bottomCellHeight, bottomCellWidth, selectedItem,
        2);
    BagItem bagItem2 = new BagItem("img/panda.png", bottomCellHeight, bottomCellWidth, selectedItem,
        2);
    List<BagItem> bagItems = new ArrayList<>();
    bagItems.add(bagItem1);
    bagItems.add(bagItem2);

    bagView = new BagView(bagItems, 5, 1);

    topAnimationView = new TopAnimationView(bagView, windowWidth, windowHeight);

    List<PlantModel> plantModelList = new ArrayList<>();
    landView = new LandView(plantModelList, gameTime, selectedItem, bagView, topAnimationView);
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
    timeLabel.setText(displayTextResource.getString("time") + " " + gameTime);
  }


  private void setupTop(BorderPane root) {
    HBox topBox = new HBox();
    topBox.setPrefSize(topWidth, topHeight);
    topBox.getStyleClass().add("top-box");
    Button btnOpenShop = new Button();
    btnOpenShop.setId("shopButton");
    btnOpenShop.setOnAction(e -> openShop());
    timeLabel.getStyleClass().add("play-top-label");
    CurrentMoneyHbox currentMoneyHbox = new CurrentMoneyHbox();
    money.addObserver(currentMoneyHbox, money.getMoney());
    topBox.getChildren().addAll(timeLabel, energyProgressBar, btnOpenShop, currentMoneyHbox);
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
    StackPane toolStackPane = bagView.getToolStackPane();
    bottomBox.getChildren().addAll(toolStackPane);
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
    ShoppingView shoppingPageView = new ShoppingView(shop, bag, stage, scene, money);
    Scene shoppingScene = new Scene(shoppingPageView.getScene());
    shoppingScene.getStylesheets().add("styles.css");
    stage.setScene(shoppingScene);
    stage.show();
  }
}
