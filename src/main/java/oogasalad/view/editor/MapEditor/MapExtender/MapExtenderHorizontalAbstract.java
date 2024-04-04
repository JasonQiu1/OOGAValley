package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oogasalad.view.editor.MapEditor.BuildableMap;

public abstract class MapExtenderHorizontalAbstract extends MapExtenderAbstract {
    public MapExtenderHorizontalAbstract(BuildableMap bm, EventHandler<MouseEvent> onActionAdd,
                                         EventHandler<MouseEvent> onActionRemove){
        super(bm, onActionAdd, onActionRemove);
        adder.setHeight(10);
        remover.setHeight(10);
        adder.setWidth(bm.getWidth());
        remover.setWidth(bm.getWidth());


        //listen for propertyChanges
        bm.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Update rectangles according to the new width
            adder.setWidth(newValue.doubleValue() / 2);
            adder.setX(newValue.doubleValue() / 2);
            remover.setWidth(newValue.doubleValue() / 2);
        });

    }
}