package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.net.MalformedURLException;

public class MapEditor extends VBox {
    //TODO: make this work
    /**
     * use modelAPI to load starting map data and types of tiles
     */
    TileSelector ts = new TileSelector();
    public MapEditor(){
        super();
        super.setAlignment(Pos.CENTER);
        Label l = new Label("Map Editor"); //Resource Bundle This
        l.getStyleClass().add("map-label");
        getChildren().add(l);
        getChildren().add(new BuildableMap(ts));
        getChildren().add(new ScrollPane(new Tiles(ts)));
    }
}
