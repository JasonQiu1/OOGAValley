package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import oogasalad.view.editor.MapEditor.BuildableMap;

public abstract class MapExtenderAbstract extends Group {
    protected Rectangle adder;
    protected Rectangle remover;
    private BuildableMap bm;
    public MapExtenderAbstract(BuildableMap bm, EventHandler<MouseEvent> onActionAdd, EventHandler<MouseEvent> onActionRemove) {
        super();
        adder = new Rectangle();
        remover = new Rectangle();
        adder.setFill(Color.GREEN);
        remover.setFill(Color.RED);
        adder.setOnMouseClicked(onActionAdd);
        remover.setOnMouseClicked(onActionRemove);
        super.getChildren().addAll(adder, remover);
    }

}
