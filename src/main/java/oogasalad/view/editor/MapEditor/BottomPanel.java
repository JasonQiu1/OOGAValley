package oogasalad.view.editor.MapEditor;

import javafx.scene.layout.StackPane;

public class BottomPanel extends StackPane {

  public BottomPanel(Selector ts) {
    super(new TilesWrapper(new SelectableViews(ts), "Tiles"));
  }
}
