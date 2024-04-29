package oogasalad.view.editor;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import oogasalad.model.data.GameConfiguration;

import java.io.IOException;
import java.util.function.Supplier;

public class SaveAllButton extends Button {
    public SaveAllButton(GameConfiguration gc, Supplier<String> getFileName) {
        super("Save All"); //TODO: Resource Bundle
        setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Save");
            alert.getDialogPane().setContent(new Label("Are you sure?"));
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                try {
                    gc.save(getFileName.get());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
                });

    }
}
