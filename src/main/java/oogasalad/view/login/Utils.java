package oogasalad.view.login;

import javafx.scene.control.Alert;

/**
 * This class is used to store the user information
 */
public class Utils {

  public static void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

}
