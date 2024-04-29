package oogasalad.view.login;

import javafx.application.Application;
import javafx.stage.Stage;
import oogasalad.database.realtime.Firebase;

/**
 * This class is responsible for displaying the new load screen for the game. This screen will allow
 * the user to either login, start a new game, load a game, or go back to the previous screen.
 */

public class MultiView extends Application {

  private Stage primaryStage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Firebase.initializeFirebase();
    this.primaryStage = primaryStage;
    showStartGameScene();
    primaryStage.setTitle("Game Lobby");
    primaryStage.show();
  }

  public void showStartGameScene() {
    StartGameScene startGameScene = new StartGameScene(this);
    primaryStage.setScene(startGameScene.getScene());
  }

  public void showJoinGameScene() {
    JoinGameScene joinGameScene = new JoinGameScene(this);
    primaryStage.setScene(joinGameScene.getScene());
  }
}
