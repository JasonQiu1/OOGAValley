package oogasalad.view.editor.MapEditor;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

//TODO: resource bundle this
class SizeChangeDialogBox {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.MapEditor.SizeChangeDialogBox.";
  private String displayTextLanguage = "EnglishDisplayText";
  private ResourceBundle displayTextResource;

  public int[] getNewSize() {
    displayTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + displayTextLanguage);

    TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    HBox hbox = new HBox(new Label(displayTextResource.getString("prompt") + "  "), textField1,
        textField2);
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.getDialogPane().setContent(hbox);
    alert.setTitle(displayTextResource.getString("change_grid_size"));
    alert.setHeaderText(null);
    alert.showAndWait();

    ButtonType result = alert.getResult();
    if (result == ButtonType.OK) {
      try {
        int newI = Integer.parseInt(textField1.getText());
        int newJ = Integer.parseInt(textField2.getText());
        if (checkOutOfBounds(newI, newJ)) {
          showErrorPopup(displayTextResource.getString("invalid_range"), displayTextResource.getString("error_instruction"));
          return null;
        }
        return new int[]{newI, newJ};
      } catch (NumberFormatException e) {
        showErrorPopup(displayTextResource.getString("invalid_type"), displayTextResource.getString("error_instruction"));
        return null; // Return null if parsing fails
      }
    } else {
      // User clicked cancel or X button
      return null;
    }
  }


  private boolean checkOutOfBounds(int newI, int newJ) {
    return newI < 1 || newJ < 1;
  }

  private void showErrorPopup(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}