package oogasalad.view.shopping;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorPopUp {
  public static void display(String errorMessage) {
    Stage window = new Stage();

    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Error");

    Label label = new Label(errorMessage);
    Button closeButton = new Button("Close");
    closeButton.setOnAction(e -> window.close());

    VBox layout = new VBox(10);
    layout.getChildren().addAll(label, closeButton);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout, 300, 150);
    scene.getStylesheets().add("styles.css");
    scene.getRoot().getStyleClass().add("error-popup");
    window.setScene(scene);
    window.showAndWait();
  }
}
