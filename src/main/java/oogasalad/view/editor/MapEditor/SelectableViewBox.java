package oogasalad.view.editor.MapEditor;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class SelectableViewBox extends HBox {

  public SelectableViewBox(List<SelectableView> selectables) {
    super();
    selectables.stream().map(node -> {
      VBox vbox = new VBox(node, node.getLabel());
      vbox.setAlignment(Pos.CENTER);
      vbox.setId(node.getLabel().getText());
      Selector.add(vbox);
      return vbox;
    }).forEach(super.getChildren()::add);
    super.setSpacing(8);
  }
}
