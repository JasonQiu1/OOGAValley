package oogasalad.view.editor.GameObjectEditor;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ResourceBundle;

public class RemoveObjectDialogBox {
    private static final String DEFAULT_RESOURCE_PACKAGE =
            "view.editor.GameObjectEditor.RemoveObjectDialogBox.";
    private final String displayTextLanguage = "EnglishDisplayText";
    private ResourceBundle displayTextResource;

    // Method to get new size from the user
    public String[] getNewField() {
        displayTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + displayTextLanguage);

        TextField textField1 = new TextField();
        textField1.setId("toBeRemoved");
        HBox hbox = new HBox(new Label(displayTextResource.getString("prompt") + "  "), textField1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setContent(hbox);
        alert.setTitle(displayTextResource.getString("remove_object"));
        alert.setHeaderText(null);
        alert.showAndWait();

        ButtonType result = alert.getResult();
        if (result == ButtonType.OK) {
            return new String[]{textField1.getText()};
        } else {
            return null; // User clicked cancel or X button
        }
    }
}
