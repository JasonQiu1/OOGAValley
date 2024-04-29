package oogasalad.view.editor.GameObjectEditor;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.ResourceBundle;

public class AddNewObjectDialogBox {
    private static final String DEFAULT_RESOURCE_PACKAGE =
            "view.editor.GameObjectEditor.AddNewObjectDialogBox.";
    private final String displayTextLanguage = "EnglishDisplayText";
    private ResourceBundle displayTextResource;

    // Method to get new size from the user
    public String[] getNewField() {
        displayTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + displayTextLanguage);

        TextField textField1 = new TextField();
        textField1.setId("newType");
        TextField textField2 = new TextField();

        textField2.setId("newName");
        HBox hbox = new HBox(new Label(displayTextResource.getString("prompt") + "  "), textField1,
                textField2);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setContent(hbox);
        alert.setTitle(displayTextResource.getString("add_new_object"));
        alert.setHeaderText(null);
        alert.showAndWait();

        ButtonType result = alert.getResult();
        if (result == ButtonType.OK) {
            return new String[]{textField1.getText(), textField2.getText()};
        } else {
            return null; // User clicked cancel or X button
        }
    }

}
