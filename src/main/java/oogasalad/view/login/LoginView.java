package oogasalad.view.login;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oogasalad.database.realtime.Firebase;
import oogasalad.model.api.GameInterface;

/**
 * This class is responsible for displaying the new load screen for the game. This screen will allow
 * the user to either login, start a new game, load a game, or go back to the previous screen.
 */

public class LoginView extends Application {

  private Label helloLabel;
  private UserInfo userInfo;
  private GameInterface game;
  private VBox vbox;

  public LoginView(GameInterface game) {
    super();
    this.game = game;
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Firebase.initializeFirebase();
    Button loginButton = new Button("Login");
    userInfo = new UserInfo(-1, "Guest");

    loginButton.setOnAction(event -> {
      Login login = new Login(primaryStage, primaryStage.getScene(), game);
      primaryStage.setScene(new Scene(login.getScene()));
      primaryStage.show();
      login.setOnLoginSuccess((username, id) -> {
        helloLabel.setText("Welcome " + username + "!");
        userInfo = new UserInfo(id, username);
      });
    });


    vbox = new VBox(10);
    vbox.getChildren().addAll(loginButton);
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
