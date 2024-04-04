package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import oogasalad.view.editor.MapEditor.BuildableMap;

public class MapExtenderTop extends MapExtenderHorizontalAbstract{
    public MapExtenderTop(BuildableMap bm, EventHandler<MouseEvent> onActionAdd, EventHandler<MouseEvent> onActionRemove) {
        super(bm, onActionAdd, onActionRemove);
        adder.setX(0);
        remover.setX(bm.getWidth() / 2);
        adder.setY(0);
        remover.setY(0);
    }
}
