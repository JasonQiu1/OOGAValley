package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderHorizontal;
import oogasalad.view.editor.MapEditor.MapExtender.MapExtenderVertical;

public class MapExtensionContainer extends StackPane {

  public MapExtensionContainer(BuildableMap bm) {
    super();
    HBox topBox = new HBox(new MapExtenderHorizontal(bm,
        e -> bm.addRowTop(), e -> bm.removeRowTop()));
    topBox.setAlignment(Pos.TOP_CENTER);

    HBox bottomBox = new HBox(new MapExtenderHorizontal(bm,
        e -> bm.addRowBottom(), e -> bm.removeRowBottom()));
    bottomBox.setAlignment(Pos.BOTTOM_CENTER);

    HBox rightBox = new HBox(new MapExtenderVertical(bm,
        e -> bm.addColumnRight(), e -> bm.removeColumnRight()));
    rightBox.setAlignment(Pos.CENTER_RIGHT);

    HBox leftBox = new HBox(new MapExtenderVertical(bm,
        e -> bm.addColumnLeft(), e -> bm.removeColumnLeft()));
    leftBox.setAlignment(Pos.CENTER_LEFT);

    super.getChildren().addAll(topBox, bottomBox, leftBox, rightBox);

//        bm.widthProperty().addListener((observable, oldValue, newValue) -> {
//            super.setWidth(newValue.doubleValue());
//            topBox.setMaxWidth(newValue.doubleValue());
//            bottomBox.setMaxWidth(newValue.doubleValue());
//            leftBox.setMaxWidth(newValue.doubleValue());
//            rightBox.setMaxWidth(newValue.doubleValue());
//        });
//
//        bm.heightProperty().addListener((observable, oldValue, newValue) -> {
//            topBox.setMinHeight(newValue.doubleValue());
//            bottomBox.setMinHeight(newValue.doubleValue());
//            leftBox.setMinHeight(newValue.doubleValue());
//            rightBox.setMinHeight(newValue.doubleValue());
//        });
  }
}
