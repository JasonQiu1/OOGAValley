package oogasalad.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
public class ChangePageButton extends Button {
    //TODO: Resource Bundle this
    public ChangePageButton(String name, String color, EventHandler<ActionEvent> onAction){
        super(name);
        this.setStyle("-fx-font-size: 50px; -fx-background-color: " + color + ";");
        this.setMinSize(100, 100);
        this.setOnMouseEntered(event -> this.setStyle("-fx-font-size: 50px; -fx-background-color: gray;"));
        this.setOnMouseExited(event -> this.setStyle("-fx-font-size: 50px; -fx-background-color: " + color + ";"));
        this.setOnAction(onAction);
    }
}
