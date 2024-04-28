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
import oogasalad.controller.GameKeyHandler;
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

  private int windowWidth;

  private int windowHeight;

  private double landCellWidth;
  private double landCellHeight;
  private double bottomCellWidth;
  private double bottomCellHeight;
  private double bottomBoxWidth;
  private double bottomBoxHeight;
  private int landNumRows;
  private int landNumCols;
  private double topHeight;
  private double topWidth;
  private double bottomHeight;
  private double bottomWidth;
  private double padding;
  private double leftRightWidth;
  private double topButtonWidth;
  private double topButtonHeight;
  private double topFontSize;
  private double landGridPaneWidth;
  private double landGridPaneHeight;
  private double leftRightHeight;
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
  private StackPane root;

  public PlayingPageView(Stage primaryStage, String language, Scene backScene,
      GameConfiguration gameConfiguration) {
    stage = primaryStage;
    primaryLanguage = language;
    this.previousScene = backScene;
    game = gameFactory.createGame();
    energyProgress = new EnergyProgress(game);
    this.windowWidth = 800;
    this.windowHeight = 600;
    initSize();
  }

  public PlayingPageView(Stage primaryStage, String language, String fileName, int windowWidth,
      int windowHeight) throws IOException {
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
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    initSize();
  }

  private void initSize() {
    padding = windowWidth / 100;
    landNumRows = game.getGameState().getGameWorld().getHeight();
    landNumCols = game.getGameState().getGameWorld().getWidth();
    landCellWidth = windowWidth / 20;
    landCellHeight = windowWidth / 20;
    bottomCellWidth = landCellWidth / 1.5;
    bottomCellHeight = landCellHeight / 1.5;
    bottomBoxWidth = windowWidth / 2;
    bottomHeight = (windowHeight - landNumRows * landCellHeight) / 2;
    bottomBoxHeight = bottomHeight - padding;
    topWidth = windowWidth;
    topHeight = windowHeight - bottomHeight - landNumRows * landCellHeight;
    leftRightWidth = (windowWidth - landNumCols * landCellWidth) / 2;
    bottomWidth = windowWidth;
    landGridPaneWidth = landCellWidth * landNumCols;
    landGridPaneHeight = landCellHeight * landNumRows;
    leftRightHeight = windowHeight - bottomHeight - topHeight;
    topButtonWidth = topWidth / 15;
    topButtonHeight = topHeight / 3;
    topFontSize = topButtonHeight / 6;
  }

  public void save() {
    FileChooser result = new FileChooser();
    result.setTitle("save location ");
    result.setInitialDirectory(new File("data/gamesaves"));
    result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Files", "*.json"));
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
    root = new StackPane();
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
    // Set key handler for the game window
    scene.setOnKeyPressed(new GameKeyHandler(game));
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
    bagView = new BagView(game, 10, bottomCellWidth,
        bottomCellHeight,bottomBoxWidth, bottomBoxHeight);
    topAnimationView = new TopAnimationView(bagView, windowWidth, windowHeight);
    landView = new LandView(game, landGridPaneWidth, landGridPaneHeight);
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
    timeLabel.setText("" + game.getGameState().getGameTime());
  }

  private void setupTop(BorderPane root) {
    HBox topBox = new HBox();
    topBox.setPrefSize(topWidth, topHeight);
    topBox.getStyleClass().add("top-box");
    createHelpButton();
    Button menu = new Button("Menu");
    setButtonSize(menu, topButtonWidth, topButtonHeight, topFontSize);
    menu.setOnAction(event -> openAndCloseMenu());
    menu.getStyleClass().add("menu_button");
    menu.setAlignment(Pos.CENTER);
    Button btnOpenShop = new Button();
    btnOpenShop.setId("shopButton");
    btnOpenShop.setOnAction(e -> openShop());
    timeLabel.getStyleClass().add("play-top-label");
    timeLabel.setId("time-label");
    CurrentMoneyHbox currentMoneyHbox = new CurrentMoneyHbox(game);
    currentMoneyHbox.update();
    currentMoneyHbox.setAlignment(Pos.CENTER);
    Button sleepButton = new Button("sleep");
    setButtonSize(sleepButton, topButtonWidth, topButtonHeight, topFontSize);
    sleepButton.setId("sleep-button");
    sleepButton.setOnMouseClicked(event -> {
      LOG.info("slept");
      game.sleep();
    });
    Button saveButton = new Button("save");
    saveButton.setId("save-button");
    saveButton.setOnMouseClicked(event -> save());
    setButtonSize(saveButton, topButtonWidth, topButtonHeight, topFontSize);
    Button loginButton = new Button("Web");
    setButtonSize(loginButton, topButtonWidth, topButtonHeight, topFontSize);
    loginButton.setOnAction(e -> openLogin());
    topBox.getChildren()
        .addAll(menu, helpButton, sleepButton, saveButton, timeLabel, energyProgress, btnOpenShop,
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
    VBox rightBox = new VBox();
    rightBox.setPrefSize(leftRightWidth, leftRightHeight);
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

  private void setButtonSize(Button button, double width, double height, double fontSize) {
    button.setPrefWidth(width);
    button.setPrefHeight(height);
    button.setStyle(String.format("-fx-font-size: %.1fpx;", fontSize));
  }


  public StackPane getRoot() {
    return root;
  }
  public void setWindowWidth(int windowWidth) {
    this.windowWidth = windowWidth;
  }

  public void setWindowHeight(int windowHeight) {
    this.windowHeight = windowHeight;
  }
}
