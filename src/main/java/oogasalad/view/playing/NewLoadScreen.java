package oogasalad.view.playing;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oogasalad.view.login.Login;
import oogasalad.view.login.UserInfo;

/**
 * This class is responsible for displaying the new load screen for the game. This screen will allow
 * the user to either login, start a new game, load a game, or go back to the previous screen.
 */

public class NewLoadScreen extends Application {

  private Label helloLabel;
  private UserInfo userInfo;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Button loginButton = new Button("Login");
    Button newButton = new Button("New");
    Button loadButton = new Button("Load");
    Button backButton = new Button("Back");
    userInfo = new UserInfo(-1, "Guest");

    loginButton.setOnAction(event -> {
      Login login = new Login(primaryStage, primaryStage.getScene());
      primaryStage.setScene(new Scene(login.getScene()));
      primaryStage.show();
      login.setOnLoginSuccess((username, id) -> {
        helloLabel.setText("Welcome " + username + "!");
        userInfo = new UserInfo(id, username);
      });
    });

    newButton.setOnAction(event -> {
      PlayingPageView playingPage = new PlayingPageView();
      playingPage.start(primaryStage);
    });

    loadButton.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Load Game");
      File selectedFile = fileChooser.showOpenDialog(primaryStage);
      if (selectedFile != null) {
        // Load the game
      }
    });

    backButton.setOnAction(event -> {
      // Go back to the splash screen
    });

    VBox vbox = new VBox(10);
    vbox.getChildren().addAll(loginButton, newButton, loadButton, backButton);
    vbox.setAlignment(Pos.CENTER);
    BorderPane root = new BorderPane();
    root.setCenter(vbox);

    helloLabel = new Label("Welcome Guest!");
    helloLabel.setAlignment(Pos.TOP_RIGHT);
    root.setTop(helloLabel);

    Scene scene = new Scene(root, 300, 300);

    primaryStage.setScene(scene);
    primaryStage.setTitle("NewLoadView");
    primaryStage.show();
  }
}
