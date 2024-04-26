package oogasalad.view.login;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import oogasalad.database.realtime.GameService;

public class StartGameScene {

  private Scene scene;
  private MultiView gameApp;

  public StartGameScene(MultiView gameApp) {
    this.gameApp = gameApp;
    initializeScene();
  }

  private void initializeScene() {
    TextField playerNameField = new TextField();
    playerNameField.setPromptText("Enter player name");

    TextField initialScoreField = new TextField();
    initialScoreField.setPromptText("Enter initial score");

    Button startGameButton = new Button("Start New Game");
    startGameButton.setOnAction(event -> handleStartNewGame(playerNameField, initialScoreField));

    Button switchToJoinGame = new Button("Join Another Game");
    switchToJoinGame.setOnAction(event -> gameApp.showJoinGameScene());

    VBox layout = new VBox(10, playerNameField, initialScoreField, startGameButton,
        switchToJoinGame);
    layout.setPadding(new javafx.geometry.Insets(20));

    scene = new Scene(layout, 300, 250);
  }

  public Scene getScene() {
    return scene;
  }

  private void handleStartNewGame(TextField playerNameField, TextField initialScoreField) {
    try {
      String playerName = playerNameField.getText();
      int initialScore = Integer.parseInt(initialScoreField.getText());
      GameService gameService = new GameService();

      gameService.startNewGame(playerName, initialScore, success -> {
        Platform.runLater(() -> {
          if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "New game started successfully!");
          } else {
            showAlert(Alert.AlertType.ERROR, "Failure", "Failed to start a new game!");
          }
        });
      });
    } catch (NumberFormatException e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Initial score must be a number!");
    }
  }

  private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}
