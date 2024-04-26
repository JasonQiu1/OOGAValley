package oogasalad.view.editor.MapEditor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class SelectableViewBoxWrapper extends VBox {

  public SelectableViewBoxWrapper(SelectableViewBox t, String title) {
    super();
    super.setAlignment(Pos.CENTER);
    setPadding(new Insets(10, 3, 0, 3));
    ScrollPane sp = new ScrollPane(t);
    Label tile = new Label(title);
    tile.getStyleClass().add("selector-title");
    super.getChildren().addAll(tile, sp);
    super.setMinHeight(90);
  }
}
