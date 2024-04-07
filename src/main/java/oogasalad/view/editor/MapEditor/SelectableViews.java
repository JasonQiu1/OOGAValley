package oogasalad.view.editor.MapEditor;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class SelectableViews extends HBox {

  public SelectableViews(List<SelectableView> selectables, Selector ts) {
    super();
    selectables.stream()
        .map(node -> {
          VBox vbox = new VBox(node, node.getLabel());
          vbox.setAlignment(Pos.CENTER);
          ts.add(vbox);
          return vbox;
        })
        .forEach(super.getChildren()::add);
    super.setSpacing(8);
  }
}
