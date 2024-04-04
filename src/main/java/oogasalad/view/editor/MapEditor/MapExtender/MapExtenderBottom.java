package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oogasalad.view.editor.MapEditor.BuildableMap;

public class MapExtenderBottom extends MapExtenderHorizontalAbstract{
    public MapExtenderBottom(BuildableMap bm, EventHandler<MouseEvent> onActionAdd, EventHandler<MouseEvent> onActionRemove) {
        super(bm, onActionAdd, onActionRemove);
        adder.setX(0);
        remover.setX(bm.getWidth() / 2);
        adder.setY(bm.getHeight());
        remover.setY(bm.getHeight());

        bm.heightProperty().addListener((observable, oldValue, newValue) -> {
            // Update rectangles according to the new width
            adder.setY(newValue.doubleValue());
            remover.setY(newValue.doubleValue());
        });
    }
}
