package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderHorizontal;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderVertical;

public class BuildableMapWrapper extends ScrollPane {

  private final BorderPane bp;

  public BuildableMapWrapper(BuildableMap bm) {
    super();
    HBox hbox = new HBox();
    bp = getBorderPane(bm);
    hbox.getChildren().add(bp);
    hbox.setAlignment(Pos.CENTER);
    hbox.setMinSize(bm.getGridPane().getPrefWidth(), bm.getGridPane().getPrefHeight());
    Pane pane = new Pane(hbox);
    super.setContent(pane);
    bm.getGridPaneProperty().addListener((observable, oldValue, newValue) -> bp.setCenter(newValue));
  }

  private BorderPane getBorderPane(BuildableMap bm) {
    BorderPane bp = new BorderPane();
    bp.setCenter(bm.getGridPane());
    setAlignment(bm.getGridPane());

    bp.setRight(new MapExtenderVertical(bm,
        e -> bm.addColumnRight(), e -> bm.removeColumnRight()));
    setAlignment(bp.getRight());

    bp.setLeft(new MapExtenderVertical(bm,
        e -> bm.addColumnLeft(), e -> bm.removeColumnLeft()));
    setAlignment(bp.getLeft());

    bp.setTop(new MapExtenderHorizontal(bm,
        e -> bm.addRowTop(), e -> bm.removeRowTop()));
    setAlignment(bp.getTop());

    bp.setBottom(new MapExtenderHorizontal(bm,
        e -> bm.addRowBottom(), e -> bm.removeRowBottom()));
    setAlignment(bp.getBottom());

    return bp;
  }

  private void setAlignment(Node n) {
    BorderPane.setAlignment(n, Pos.CENTER);
  }

}
