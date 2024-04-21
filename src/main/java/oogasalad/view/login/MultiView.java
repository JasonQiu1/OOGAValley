package oogasalad.view.login;

import javafx.application.Application;
import javafx.stage.Stage;
import oogasalad.database.realtime.Firebase;

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
