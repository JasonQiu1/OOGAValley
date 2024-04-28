package oogasalad.view.editor.GameObjectEditor;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MapPropertiesContainer extends HBox {
    public MapPropertiesContainer(String mapPropertyName, Consumer<String> addMapProperty, Consumer<String> removeMapProperty){
        super();
        super.setAlignment(Pos.CENTER);
        setId(mapPropertyName);
        Label mapPropertiesName = new Label(mapPropertyName);
        mapPropertiesName.getStyleClass().add("map-properties-name");
        super.getChildren().addAll(mapPropertiesName,
                new AddPropertyButton("Add", addMapProperty, mapPropertyName),
                new AddPropertyButton("Remove",removeMapProperty, mapPropertyName));
    }
}
