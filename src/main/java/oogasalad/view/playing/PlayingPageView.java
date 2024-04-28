package oogasalad.view.playing;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import oogasalad.model.api.GameFactory;
import oogasalad.model.api.GameInterface;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.buttonmenu.ButtonMenu;
import oogasalad.view.gpt.Chat;
import oogasalad.view.login.LoginView;
import oogasalad.view.playing.component.BagView;
import oogasalad.view.playing.component.EnergyProgress;
import oogasalad.view.playing.component.LandView;
import oogasalad.view.playing.component.ResultPage;
import oogasalad.view.playing.component.TopAnimationView;
import oogasalad.view.shopping.ShoppingView;
import oogasalad.view.shopping.components.top.CurrentMoneyHbox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
  public static final double bottomBoxWidth = 600;
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
  public static final double windowHeight = landGridPaneHeight + topHeight + bottomHeight;
  public static final double leftRightHeight = 300;
  public static final double bottomBoxPadding = 50;
  private static final String DEFAULT_RESOURCE_PACKAGE = "view.playing.";
  private static final String DEFAULT_RESOURCE_FOLDER = "src/main/resources/view/playing/";
  private static final Logger LOG = LogManager.getLogger(PlayingPageView.class);
  private final String myLanguage = "EnglishDisplayText";
  private final String menuLanguage = "EnglishMenuButtons.csv";
  private final ResourceBundle displayTextResource =
      ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myLanguage);
  private final String menuButtons = DEFAULT_RESOURCE_FOLDER + menuLanguage;
  private final Label timeLabel = new Label();

  private final EnergyProgress energyProgress;
  private final Stage stage;
  private final String primaryLanguage;

  private final GameFactory gameFactory = new GameFactory();
  private final GameInterface game;
  private Button helpButton;
  private ButtonMenu btm;
  private LandView landView;
  private TopAnimationView topAnimationView;
  private BagView bagView;
  private Scene previousScene;

  private Timeline timeline;

  public PlayingPageView(Stage primaryStage, String language, Scene backScene,
      GameConfiguration gameConfiguration) {
    stage = primaryStage;
    primaryLanguage = language;
    this.previousScene = backScene;
    game = gameFactory.createGame();
    energyProgress = new EnergyProgress(game);
  }

  public PlayingPageView(Stage primaryStage, String language, String fileName) throws IOException {
    GameInterface gameTemp;
    stage = primaryStage;
    primaryLanguage = language;
    try {
      gameTemp = gameFactory.createGame(fileName, fileName);
    } catch (IOException e) {
      LOG.info("cannot find game saves, load from the config");
      gameTemp = gameFactory.createGame(fileName);
    }

    game = gameTemp;
    energyProgress = new EnergyProgress(game);
  }

  public void save() {
    FileChooser result = new FileChooser();
    result.setTitle("save location ");
    result.setInitialDirectory(new File("data/gamesaves"));
    result.getExtensionFilters()
        .setAll(new FileChooser.ExtensionFilter("Files", "*.json"));
    File file = result.showSaveDialog(stage);
    if (file == null) {
      return;
    }
    try {
      game.save(file.getName());
      game.getGameConfiguration().save(file.getName());
    } catch (IOException e) {
      new Alert(AlertType.ERROR, "saving failed").showAndWait();
    } catch (InvalidPathException e) {
      new Alert(AlertType.ERROR, "path invalid").showAndWait();
    }
    new Alert(AlertType.CONFIRMATION, "save done").showAndWait();
    LOG.info("saving done");
  }


  public void start() {
    LOG.info("initializing game");
    initModel();
    LOG.info("finish loading game model");
    StackPane root = new StackPane();
    root.getStyleClass().add("playing-root");
    BorderPane borderPane = new BorderPane();
    setupTop(borderPane);
    setupLeftRight(borderPane);
    setupCenter(borderPane);
    setupBottom(borderPane);

    Button menu = new Button("Menu");
    menu.setOnAction(event -> openAndCloseMenu());
    menu.getStyleClass().add("menu_button");
    StackPane.setAlignment(menu, Pos.TOP_LEFT);
    root.getChildren().addAll(borderPane, topAnimationView, menu);
    StackPane.setAlignment(borderPane, javafx.geometry.Pos.TOP_LEFT);
    StackPane.setAlignment(topAnimationView, javafx.geometry.Pos.TOP_LEFT);
    Scene scene = new Scene(root, windowWidth, windowHeight);
    scene.getStylesheets().add("styles.css");
    stage.setTitle(displayTextResource.getString("play_title"));
    setUpdate();
    stage.setScene(scene);
    stage.show();
  }

  private void openAndCloseMenu() {
    if (btm == null) {
      LOG.info("Opened Button Menu");
      btm = new ButtonMenu(stage, primaryLanguage, previousScene, menuButtons);
      btm.open();
    } else {
      btm.closeMenu();
      btm = null;
    }
  }

  private void initModel() {
    bagView = new BagView(game, 10);
    topAnimationView = new TopAnimationView(bagView, windowWidth, windowHeight);
    landView = new LandView(game);
  }

  private void setUpdate() {
    timeline = new Timeline();
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1.0 / 30), event -> {
      if (!game.isGameOver()) {
        game.update();
        landView.update();
        bagView.update();
        updateTimeLabel();
        energyProgress.update();
      } else {
        timeline.stop();
        Platform.runLater(this::endGame);
      }
    });
    timeline.getKeyFrames().add(keyFrame);
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void endGame() {
    ResultPage resultPage = new ResultPage(game, stage);
    resultPage.show();
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
    timeLabel.setId("time-label");
    CurrentMoneyHbox currentMoneyHbox = new CurrentMoneyHbox(game);
    currentMoneyHbox.update();
    Button sleepButton = new Button("sleep");
    sleepButton.setId("sleep-button");
    sleepButton.setOnMouseClicked(event -> {
      LOG.info("slept");
      game.sleep();
    });
    Button saveButton = new Button("save");
    saveButton.setId("save-button");
    saveButton.setOnMouseClicked(event -> save());
    Button loginButton = new Button("Web");
    loginButton.setOnAction(e -> openLogin());
    topBox.getChildren()
        .addAll(helpButton, sleepButton, saveButton, timeLabel, energyProgress, btnOpenShop,
            currentMoneyHbox, loginButton);
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
    StackPane toolStackPane = bagView;
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
    ShoppingView shoppingPageView = new ShoppingView(game, stage, scene, this);
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
  private void openLogin() {
    LoginView loginView = new LoginView(game);
    loginView.start(new Stage());
  }


}
