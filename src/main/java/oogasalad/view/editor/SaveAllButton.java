package oogasalad.view.editor;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import oogasalad.model.data.GameConfiguration;

import java.io.IOException;

public class SaveAllButton extends Button {
    public SaveAllButton(GameConfiguration gc) {
        super("Save All");
        setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save As");
            alert.setHeaderText(null);
            TextField tf = new TextField();
            HBox hbox = new HBox(tf);
            alert.getDialogPane().setContent(hbox);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                try {
                    gc.save(tf.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
                });

    }
}
