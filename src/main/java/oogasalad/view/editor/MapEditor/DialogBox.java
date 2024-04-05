package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//TODO: resource bundle this
class DialogBox {
    public int[] getNewSize() {
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        HBox hbox = new HBox(new Label("Enter new integer grid size (rows, columns):  "), textField1, textField2);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setContent(hbox);
        alert.setTitle("Change Grid Size");
        alert.setHeaderText(null);
        alert.showAndWait();

        ButtonType result = alert.getResult();
        if (result == ButtonType.OK) {
            try {
                int newI = Integer.parseInt(textField1.getText());
                int newJ = Integer.parseInt(textField2.getText());
                if (checkOutOfBounds(newI, newJ)) {
                    showErrorPopup("Invalid Range", "Please enter an integer between 1 and 20");
                    return null;
                }
                return new int[]{newI, newJ};
            } catch (NumberFormatException e) {
                showErrorPopup("Invalid Type", "Please enter an integer between 1 and 20");
                return null; // Return null if parsing fails
            }
        } else {
            // User clicked cancel or X button
            return null;
        }
    }


        private boolean checkOutOfBounds(int newI, int newJ) {
        return newI < 1 || newJ < 1 || newI > 20 || newJ > 20;
    }

    private void showErrorPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}