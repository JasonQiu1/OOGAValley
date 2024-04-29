package oogasalad.view.login;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import oogasalad.database.realtime.GameService;

public class JoinGameScene {

  private final MultiView multiView;
  private Scene scene;

  public JoinGameScene(MultiView multiView) {
    this.multiView = multiView;
    initializeScene();
  }

  private void initializeScene() {
    TextField targetPlayerNameField = new TextField();
    targetPlayerNameField.setPromptText("Enter existing player's name");

    TextField joiningPlayerNameField = new TextField();
    joiningPlayerNameField.setPromptText("Enter your player name");

    TextField scoreField = new TextField();
    scoreField.setPromptText("Enter your initial score");

    Button joinGameButton = new Button("Join Game");
    joinGameButton.setOnAction(
        event -> handleJoinGame(targetPlayerNameField, joiningPlayerNameField, scoreField));

    Button switchToStartGame = new Button("Start New Game");
    switchToStartGame.setOnAction(event -> multiView.showStartGameScene());

    VBox layout =
        new VBox(10, targetPlayerNameField, joiningPlayerNameField, scoreField, joinGameButton,
            switchToStartGame);
    layout.setPadding(new javafx.geometry.Insets(20));

    scene = new Scene(layout, 300, 250);
  }

  public Scene getScene() {
    return scene;
  }

  private void handleJoinGame(TextField targetPlayerNameField, TextField joiningPlayerNameField,
      TextField scoreField) {
    String targetPlayerName = targetPlayerNameField.getText();
    String joiningPlayerName = joiningPlayerNameField.getText();
    int initialScore = 0;
    try {
      initialScore = Integer.parseInt(scoreField.getText());
    } catch (NumberFormatException e) {
      showAlert(Alert.AlertType.ERROR, "Error", "Initial score must be a number!");
      return;
    }

    GameService gameService = new GameService();
    gameService.joinGameByPlayerName(targetPlayerName, joiningPlayerName, initialScore, success -> {
      Platform.runLater(() -> {
        if (success) {
          showAlert(Alert.AlertType.INFORMATION, "Success",
              "You have successfully joined the game!");
        } else {
          showAlert(Alert.AlertType.ERROR, "Failure", "Failed to join the game!");
        }
      });
    });
  }

  private void showAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

}
