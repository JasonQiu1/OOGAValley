package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import oogasalad.view.editor.MapEditor.BuildableMap;

public class MapExtenderHorizontal extends MapExtenderAbstract {
    public MapExtenderHorizontal(BuildableMap bm, EventHandler<MouseEvent> onActionAdd,
                                 EventHandler<MouseEvent> onActionRemove){
        super(bm, onActionAdd, onActionRemove);
        adder.setHeight(10);
        remover.setHeight(10);
        adder.setWidth(bm.getWidth());
        remover.setWidth(bm.getWidth());


        //listen for propertyChanges
        bm.widthProperty().addListener((observable, oldValue, newValue) -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                adder.setWidth(newValue.doubleValue() / 2);
                adder.setX(newValue.doubleValue() / 2);
                remover.setWidth(newValue.doubleValue() / 2);
            }));
            timeline.play();
        });

    }
}