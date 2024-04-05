package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class TilesWrapper extends VBox {

  public TilesWrapper(Tiles t) {
    super();
    ScrollPane sp = new ScrollPane(t);
    Label tile = new Label("Tiles");
    tile.getStyleClass().add("tile-title");
    super.getChildren().addAll(tile, sp);
    super.setMinHeight(70);
  }
}
