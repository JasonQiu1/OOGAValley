package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oogasalad.view.editor.MapEditor.BuildableMap;

public class MapExtenderRight extends MapExtenderVerticalAbstract{
    public MapExtenderRight(BuildableMap bm, EventHandler<MouseEvent> onActionAdd, EventHandler<MouseEvent> onActionRemove) {
        super(bm, onActionAdd, onActionRemove);
        adder.setX(bm.getWidth());
        remover.setX(bm.getWidth());
        adder.setY(0);
        remover.setY(bm.getHeight() / 2);

        bm.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Update rectangles according to the new width
            adder.setX(newValue.doubleValue());
            remover.setX(newValue.doubleValue());
        });
    }
}
