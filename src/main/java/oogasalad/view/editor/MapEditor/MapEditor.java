package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


public class MapEditor extends VBox {
    //TODO: make this work

    public MapEditor(){
        super();
        super.setAlignment(Pos.CENTER);
        /**
         * use modelAPI to load starting map data and types of tiles
         */
        TileSelector ts = new TileSelector();
        BuildableMap bm = new BuildableMap(ts);
        TopPanel tp = new TopPanel(bm);
        BuildableMapWrapper bmw = new BuildableMapWrapper(bm);
        BottomPanel bp = new BottomPanel(ts);
        getChildren().add(tp);
        getChildren().add((bmw));
        getChildren().add(bp);
        //getChildren().add(new MapExtender(bm));
    }
}
