package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oogasalad.view.editor.MapEditor.BuildableMap;

public abstract class MapExtenderVerticalAbstract extends MapExtenderAbstract {
    public MapExtenderVerticalAbstract(BuildableMap bm, EventHandler<MouseEvent> onActionAdd,
                                       EventHandler<MouseEvent> onActionRemove){
        super(bm, onActionAdd, onActionRemove);
        adder.setHeight(bm.getHeight());
        remover.setHeight(bm.getHeight());
        adder.setWidth(10);
        remover.setWidth(10);

        //listen for propertyChanges
        bm.heightProperty().addListener((observable, oldValue, newValue) -> {
            // Update rectangles according to the new width
            adder.setHeight(newValue.doubleValue() / 2);
            remover.setHeight(newValue.doubleValue() / 2);
            remover.setY(newValue.doubleValue() / 2);
        });
    }
}
