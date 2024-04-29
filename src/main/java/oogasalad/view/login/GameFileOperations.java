package oogasalad.view.login;

import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oogasalad.database.GameSaveData;
import oogasalad.database.InfoService;
import oogasalad.model.api.GameInterface;
import oogasalad.view.playing.PlayingPageView;

/**
 * This class is responsible for displaying the new load screen for the game. This screen will allow
 * the user to either login, start a new game, load a game, or go back to the previous screen.
 */
public class GameFileOperations {

  private Stage stage;
  private Scene previousScene;
  private int userId;
  private GameInterface game;


  /**
   * This is the constructor for the GameFileOperations class.
   *
   * @param stage
   * @param previousScene
   * @param userId
   * @param game
   */
  public GameFileOperations(Stage stage, Scene previousScene, int userId, GameInterface game) {
    this.stage = stage;
    this.previousScene = previousScene;
    this.userId = userId;
    this.game = game;
  }

  public Scene createScene() {
    VBox vbox = new VBox();
    vbox.setPrefWidth(300);
    vbox.setPrefHeight(200);
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(20));

    Button loadButton = new Button("Load Game");
    loadButton.setId("loadButton");
    loadButton.setOnAction(e -> {
      try {
        loadGame();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    Button saveButton = new Button("Save Game");
    saveButton.setId("saveButton");
    saveButton.setOnAction(e -> {
      try {
        saveGame();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    Button sendThoughtButton = new Button("Center");
    sendThoughtButton.setId("sendThoughtButton");
    sendThoughtButton.setOnAction(e -> {
      ThoughtsView thoughtsView = new ThoughtsView(stage, previousScene);
      stage.setScene(thoughtsView.getScene());
    });

    Button backButton = new Button("Back");
    backButton.setId("backButton");
    backButton.setOnAction(e -> stage.setScene(previousScene));

    Button logoutButton = new Button("Logout");
    logoutButton.setId("logoutButton");
    logoutButton.setOnAction(event -> {
      UserSession.clearAllPreferences();
      stage.close();
    });

    vbox.getChildren().addAll(loadButton, saveButton, sendThoughtButton, backButton, logoutButton);

    return new Scene(vbox);
  }

  private void loadGame() throws IOException {

    GameSaveData saveData = InfoService.loadLatestGameData(userId);
    FileUtility.saveJsonToFile(userId, saveData.getGameSave(), saveData.getGameConfiguration(),
        saveData.getConfigurableStores());
    if (saveData != null) {
      System.out.println("Game loaded successfully!");
    } else {
      System.out.println("Failed to load game!");
    }

    PlayingPageView playingPageView = new PlayingPageView(stage, "English", userId + "",
        userId + "",
        800, 600);
    playingPageView.start();
  }

  private void saveGame() throws IOException {
    game.save(userId + ".json");
    game.getGameConfiguration().save(userId + ".json");
    String[] saves = FileUtility.readFileAsString(userId);
    boolean result = InfoService.saveGameData(userId, saves[0], saves[1], saves[2]);
    if (result) {
      System.out.println("Game saved successfully!");
    } else {
      System.out.println("Failed to save game!");
    }
  }
}
