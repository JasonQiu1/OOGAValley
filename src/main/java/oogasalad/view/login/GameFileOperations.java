package oogasalad.view.login;

import com.google.gson.Gson;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oogasalad.database.info.GameSaveData;
import oogasalad.database.info.InfoService;
import oogasalad.model.api.GameInterface;
import oogasalad.view.playing.PlayingPageView;

public class GameFileOperations {

  private Stage stage;
  private Scene previousScene;
  private int userId;
  private GameInterface game;

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
    loadButton.setOnAction(e -> {
      try {
        loadGame();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    Button saveButton = new Button("Save Game");
    saveButton.setOnAction(e -> {
      try {
        saveGame();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> stage.setScene(previousScene));

    vbox.getChildren().addAll(loadButton, saveButton, backButton);

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
    PlayingPageView playingPageView = new PlayingPageView(stage, "English",userId+"",
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
