package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class TilesWrapper extends VBox {

  public TilesWrapper(SelectableViews t, String title) {
    super();
    ScrollPane sp = new ScrollPane(t);
    Label tile = new Label(title);
    tile.getStyleClass().add(title + "-title");
    super.getChildren().addAll(tile, sp);
    super.setMinHeight(70);
  }
}
