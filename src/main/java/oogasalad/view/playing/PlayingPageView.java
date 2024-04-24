package oogasalad.view.playing;

import java.io.IOException;
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
import oogasalad.model.api.GameFactory;
import oogasalad.model.api.GameInterface;
import oogasalad.model.gameplay.GameTime;
import oogasalad.model.shop.Bag;
import oogasalad.model.shop.Shop;
import oogasalad.view.gpt.Chat;
import oogasalad.view.playing.component.BagItem;
import oogasalad.view.playing.component.BagView;
import oogasalad.view.playing.component.LandView;
import oogasalad.view.playing.component.Money;
import oogasalad.view.playing.component.SelectedItem;
import oogasalad.view.playing.component.TopAnimationView;
import oogasalad.view.shopping.ShoppingView;
import oogasalad.view.shopping.components.top.CurrentMoneyHbox;
import oogasalad.view.start.StartScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is the view for the playing page. It displays the land grid, tools, and items. It also
 * has a timer and a progress bar for energy. It has a timeline that updates the land grid every
 * second.
 */

public class PlayingPageView {


  private static final String DEFAULT_RESOURCE_PACKAGE = "view.playing.";
  private final String myLanguage = "EnglishDisplayText";
  private final ResourceBundle displayTextResource = ResourceBundle.getBundle(
      DEFAULT_RESOURCE_PACKAGE + myLanguage);

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
  private final SelectedItem selectedItem = new SelectedItem();
  private final Stage stage;
  private final Bag bag = new Bag();

  private Button helpButton;
  private LandView landView;
  private TopAnimationView topAnimationView;
  private final Money money = new Money(100);
  private final Shop shop = new Shop(money);
  private GameFactory gameFactory = new GameFactory();

  private static final Logger LOG = LogManager.getLogger(StartScreen.class);

  private GameInterface game;

  private BagView bagView;

  public PlayingPageView(Stage primaryStage) {
    stage = primaryStage;
  }

  public void start() {
    String fileName = "testWorld1.json";
    LOG.info("initializing game");
    try {
      game = gameFactory.createGame(fileName, fileName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    LOG.info("finish loading game model");
    initModel();
    StackPane root = new StackPane();
    root.getStyleClass().add("playing-root");
    BorderPane borderPane = new BorderPane();
    setupTop(borderPane);
    setupLeftRight(borderPane);
    setupCenter(borderPane);
    setupBottom(borderPane);
    root.getChildren().addAll(borderPane, topAnimationView);
    StackPane.setAlignment(borderPane, javafx.geometry.Pos.TOP_LEFT);
    StackPane.setAlignment(topAnimationView, javafx.geometry.Pos.TOP_LEFT);
    Scene scene = new Scene(root, windowWidth, windowHeight);
    scene.getStylesheets().add("styles.css");
    stage.setTitle(displayTextResource.getString("play_title"));
    setUpdate();
    stage.setScene(scene);
    stage.show();
  }

  private void initModel() {
    List<BagItem> bagItems = new ArrayList<>();
//    Map<ReadOnlyItem, Integer> items = game.getGameState().getBag().getItems();
//    for (Map.Entry<ReadOnlyItem, Integer> item : items.entrySet()) {
//      bagItems.add(new BagItem(item.getKey().getImagePath(), bottomCellWidth, bottomCellWidth,
//          selectedItem, item.getValue()));
//    }
    bagView = new BagView(bagItems, 5, 1, bag);
    topAnimationView = new TopAnimationView(bagView, windowWidth, windowHeight);
    landView = new LandView(game.getGameState().getGameWorld());
  }

  private void setUpdate() {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), event -> {
      game.update();
      landView.update();
      updateTimeLabel();
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void updateTimeLabel() {
    timeLabel.setText(
        displayTextResource.getString("time") + " " + game.getGameState().getGameTime());
  }

  private void setupTop(BorderPane root) {
    HBox topBox = new HBox();
    topBox.setPrefSize(topWidth, topHeight);
    topBox.getStyleClass().add("top-box");
    createHelpButton();
    Button btnOpenShop = new Button();
    btnOpenShop.setId("shopButton");
    btnOpenShop.setOnAction(e -> openShop());
    timeLabel.getStyleClass().add("play-top-label");
    CurrentMoneyHbox currentMoneyHbox = new CurrentMoneyHbox();
    money.addObserver(currentMoneyHbox, money.getMoney());
    topBox.getChildren()
        .addAll(helpButton, timeLabel, energyProgressBar, btnOpenShop, currentMoneyHbox);
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

  private void createHelpButton() {
    helpButton = new Button();
    helpButton.setId("help-button");
    helpButton.setOnAction(e -> {
      Stage chatStage = new Stage();
      Chat chatApp = new Chat(chatStage);
      chatApp.start();
    });
  }
}
