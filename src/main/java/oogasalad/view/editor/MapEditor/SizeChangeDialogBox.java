package oogasalad.view.editor.MapEditor;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

class SizeChangeDialogBox {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.MapEditor.SizeChangeDialogBox.";
  private String displayTextLanguage = "EnglishDisplayText";
  private ResourceBundle displayTextResource;

  // Method to get new size from the user
  public int[] getNewSize() {
    displayTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + displayTextLanguage);

    TextField textField1 = new TextField();
    textField1.setId("newRows");
    TextField textField2 = new TextField();
    textField2.setId("newColumns");
    HBox hbox = new HBox(new Label(displayTextResource.getString("prompt") + "  "), textField1,
            textField2);
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.getDialogPane().setContent(hbox);
    alert.setTitle(displayTextResource.getString("change_grid_size"));
    alert.setHeaderText(null);
    alert.showAndWait();

    ButtonType result = alert.getResult();
    if (result == ButtonType.OK) {
      return processInput(textField1.getText(), textField2.getText());
    } else {
      return null; // User clicked cancel or X button
    }
  }

  // Process user input and validate
  private int[] processInput(String text1, String text2) {
    try {
      int newI = Integer.parseInt(text1);
      int newJ = Integer.parseInt(text2);
      if (checkOutOfBounds(newI, newJ)) {
        showErrorPopup(displayTextResource.getString("invalid_range"), displayTextResource.getString("error_instruction"));
        return null;
      }
      return new int[]{newI, newJ};
    } catch (NumberFormatException e) {
      showErrorPopup(displayTextResource.getString("invalid_type"), displayTextResource.getString("error_instruction"));
      return null; // Return null if parsing fails
    }
  }

  // Check if the input size is out of bounds
  private boolean checkOutOfBounds(int newI, int newJ) {
    return newI < 1 || newJ < 1;
  }

  // Show error popup if input is invalid
  private void showErrorPopup(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
