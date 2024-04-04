package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderAbstract;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderHorizontal;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderVertical;

public class BuildableMapWrapper extends HBox {
    private BorderPane bp;
    public BuildableMapWrapper(BuildableMap bm){
        super();
        bp = getBorderPane(bm);
        super.getChildren().add(bp);
        super.setAlignment(Pos.CENTER);
        super.setMinSize(bm.getGridPane().getPrefWidth(), bm.getGridPane().getPrefHeight());
        bm.setBmw(this);
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

    private void setAlignment(Node n){
        BorderPane.setAlignment(n, Pos.CENTER);
    }

    public void updateGrid(GridPane gp){
        bp.setCenter(gp);
    }
}
