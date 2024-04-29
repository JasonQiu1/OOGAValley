package oogasalad.view.login;

import static oogasalad.view.login.Utils.showAlert;

import java.util.function.BiConsumer;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oogasalad.database.InfoService;
import oogasalad.model.api.GameInterface;
import oogasalad.view.branch.BranchBase;

/**
 * This class is responsible for displaying the login page for the game.
 */

public class Login extends BranchBase {

  private String validUsername;
  private int id;
  private BiConsumer<String, Integer> onLoginSuccessCallback;
  private GameInterface game;

  /**
   * This is the constructor for the Login class.
   *
   * @param stage
   * @param previousScene
   */
  public Login(Stage stage, Scene previousScene, GameInterface game) {
    super(stage, previousScene);
    this.game = game;
  }

  public void setOnLoginSuccess(BiConsumer<String, Integer> callback) {
    this.onLoginSuccessCallback = callback;
  }

  private void invokeOnLoginSuccessCallback() {
    if (onLoginSuccessCallback != null) {
      onLoginSuccessCallback.accept(validUsername, id);
    }
  }

  public Parent getScene() {
    VBox vbox = new VBox();
    vbox.setPrefWidth(300.0);
    vbox.setPrefHeight(300.0);
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(10));

    Label titleLabel = new Label("Login");
    titleLabel.setStyle("-fx-font-size: 24px;");

    Label usernameLabel = new Label("Username:");
    TextField usernameField = new TextField();
    usernameField.setId("usernameField");

    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();
    passwordField.setId("passwordField");

    Button registerButton = new Button("Register");
    registerButton.setId("registerButton");
    registerButton.setOnAction(e -> {
      Register registerView = new Register(getStage(), getStage().getScene());
      getStage().setScene(new Scene(registerView.getScene()));
      getStage().show();
    });

    Button submitButton = new Button("Submit");
    submitButton.setId("submitButton");
    submitButton.setOnAction(e -> {
      String username = usernameField.getText();
      String password = passwordField.getText();
      if (InfoService.isValidUser(username, password)) {
        validUsername = username;
        id = InfoService.getUserId(username);
        invokeOnLoginSuccessCallback();
        invokeOnLoginSuccessCallback();
        GameFileOperations gameOps = new GameFileOperations(getStage(), getStage().getScene(), id,
            game);
        getStage().setScene(gameOps.createScene());
      } else {
        showAlert("Login Failed", "Invalid username or password");
      }
    });
    Button back = new Button("back");
    back.setId("backButton");
    back.setOnMouseClicked(event -> {
      getStage().setScene(getPreviousScene());
    });
    vbox.getChildren()
        .addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField,
            submitButton, registerButton, back);
    return vbox;
  }

}
