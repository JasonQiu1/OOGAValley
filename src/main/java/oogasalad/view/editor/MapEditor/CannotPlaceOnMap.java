package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Alert;

public class CannotPlaceOnMap {

    //TODO: Resource Bundle
    public CannotPlaceOnMap(String type) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cannot Place On Map");
        alert.setHeaderText(null);
        alert.setContentText("Cannot Place Type " + type + " On Map");
        alert.showAndWait();
    }
}
