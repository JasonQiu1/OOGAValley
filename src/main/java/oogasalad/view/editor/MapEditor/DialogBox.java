package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

class DialogBox {
    public int[] getNewSize() {
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        VBox vbox = new VBox(new Label("Enter new grid size (rows, columns):"), textField1, textField2);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();

        try {
            int newI = Integer.parseInt(textField1.getText());
            int newJ = Integer.parseInt(textField2.getText());
            return new int[]{newI, newJ};
        } catch (NumberFormatException e) {
            return null; // Return null if parsing fails
        }
    }
}