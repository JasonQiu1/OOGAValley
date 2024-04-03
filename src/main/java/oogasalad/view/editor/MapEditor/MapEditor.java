package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


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
        BuildableMap bm = new BuildableMap(ts);
        HBox bhbm = new HBox(bm);
        bhbm.setAlignment(Pos.CENTER);
        HBox hbscb = new HBox();
        hbscb.setMinWidth(650);
        SizeChangeButton scb = new SizeChangeButton((newI, newJ) -> {
            // Show dialog to get new grid size
            DialogBox dialog = new DialogBox();
            int[] newSize = dialog.getNewSize();
            if (newSize != null) {
                // If user inputs new size, call modifyGridSize method
                bm.modifyGridSize(newSize[1], newSize[0]);
            }
        });


        hbscb.getChildren().add(scb);
        hbscb.setAlignment(Pos.BOTTOM_RIGHT);
        getChildren().add(new StackPane(l, hbscb));
        getChildren().add(bhbm);
        getChildren().add(new ScrollPane(new Tiles(ts)));
    }
}
