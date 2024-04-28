package oogasalad.view.start;

import javafx.scene.control.Button;

public class ChangePageButton extends Button {

  //TODO: Resource Bundle this
  public ChangePageButton(String name, String color) {
    super(name);
    this.setStyle("-fx-background-color: " + color + ";");
    this.setMinSize(100, 50);
    this.setOnMouseEntered(event -> this.setStyle("-fx-background-color: gray;"));
    this.setOnMouseExited(event -> this.setStyle("-fx-background-color: " + color + ";"));

    this.setId(name);
  }
}
