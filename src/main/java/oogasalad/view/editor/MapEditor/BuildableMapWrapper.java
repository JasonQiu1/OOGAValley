package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderHorizontal;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderVertical;

public class BuildableMapWrapper extends HBox {
    public BuildableMapWrapper(BuildableMap bm){
        super();
        BorderPane bp = getBorderPane(bm);
        super.getChildren().add(bp);
        super.setAlignment(Pos.CENTER);
        super.setPrefSize(bm.getPrefWidth(), bm.getPrefHeight());
    }

    private BorderPane getBorderPane(BuildableMap bm) {
        BorderPane bp = new BorderPane();
        bp.setCenter(bm);
        bp.setRight(new MapExtenderVertical(bm,
                e -> bm.addColumnRight(), e -> bm.removeColumnRight()));
        bp.setLeft(new MapExtenderVertical(bm,
                e -> bm.addColumnLeft(), e -> bm.removeColumnLeft()));
        bp.setTop(new MapExtenderHorizontal(bm,
                e -> bm.addRowTop(), e -> bm.removeRowTop()));
        bp.setBottom(new MapExtenderHorizontal(bm,
                e -> bm.addRowBottom(), e -> bm.removeRowBottom()));
        return bp;
    }
}
