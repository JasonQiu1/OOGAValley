package oogasalad.view.editor.MapEditor;

import javafx.scene.layout.StackPane;

public class BottomPanel extends StackPane {

  public BottomPanel(TileSelector ts) {
    super(new TilesWrapper(new Tiles(ts)));
  }
}
