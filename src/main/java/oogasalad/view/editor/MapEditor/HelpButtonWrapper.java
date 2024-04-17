package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class HelpButtonWrapper extends HBox {

  public HelpButtonWrapper(HelpButton hb) {
    super(hb);
    super.setAlignment(Pos.BOTTOM_LEFT);
    super.setMinWidth(325);
  }
}
