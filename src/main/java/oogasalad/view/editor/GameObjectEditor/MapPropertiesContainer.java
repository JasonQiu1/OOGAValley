package oogasalad.view.editor.GameObjectEditor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;

public class MapPropertiesContainer extends HBox {
    public MapPropertiesContainer(List<Node> nodes, String mapPropertyName){
        super();
        super.setAlignment(Pos.CENTER);
        Label mapPropertiesName = new Label(mapPropertyName);
        mapPropertiesName.getStyleClass().add("map-properties-name");
        super.getChildren().addAll(mapPropertiesName, new AddPropertyButton("Add"), new AddPropertyButton("Remove"));
    }
}
