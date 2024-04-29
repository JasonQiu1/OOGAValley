package oogasalad.view.login;

import static oogasalad.view.login.Utils.showAlert;

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
import oogasalad.view.branch.BranchBase;

/**
 * This class is responsible for displaying the registration page for the game.
 */
public class Register extends BranchBase {

  private final Button back = new Button("Back");

  /**
   * This is the constructor for the Register class.
   *
   * @param stage
   * @param previousScene
   */
  public Register(Stage stage, Scene previousScene) {
    super(stage, previousScene);
    back.setOnMouseClicked(event -> {
      getStage().setScene(getPreviousScene());
    });
  }

  public Parent getScene() {

    // Create layout components for registration form
    VBox vbox = new VBox();
    vbox.setSpacing(10);
    vbox.setPadding(new Insets(10));
    vbox.setPrefWidth(300.0);
    vbox.setPrefHeight(300.0);

    Label titleLabel = new Label("Register");
    titleLabel.setStyle("-fx-font-size: 24px;");

    Label usernameLabel = new Label("Username:");
    TextField usernameField = new TextField();

    Label emailLabel = new Label("Email:");
    TextField emailField = new TextField();

    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();

    Button registerButton = new Button("Register");
    registerButton.setOnAction(e -> {
      String username = usernameField.getText();
      String email = emailField.getText();
      String password = passwordField.getText();

      if (InfoService.addUser(username, email, password)) {
        showAlert("Registration Successful", "User registered successfully");
      } else {
        showAlert("Registration Failed", "Username already exists");
      }

    });

    vbox.getChildren()
        .addAll(titleLabel, usernameLabel, usernameField, emailLabel, emailField, passwordLabel,
            passwordField, registerButton, back);
    return vbox;
  }
}

