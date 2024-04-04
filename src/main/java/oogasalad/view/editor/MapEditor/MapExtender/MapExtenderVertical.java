package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import oogasalad.view.editor.MapEditor.BuildableMap;

public class MapExtenderVertical extends MapExtenderAbstract {
    public MapExtenderVertical(BuildableMap bm, EventHandler<MouseEvent> onActionAdd,
                               EventHandler<MouseEvent> onActionRemove){
        super(bm, onActionAdd, onActionRemove);
        adder.setHeight(bm.getGridPane().getHeight());
        remover.setHeight(bm.getGridPane().getHeight());
        adder.setWidth(10);
        remover.setWidth(10);

        //listen for propertyChanges
        bm.getGridPane().heightProperty().addListener((observable, oldValue, newValue) -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                adder.setHeight(newValue.doubleValue() / 2);
                remover.setHeight(newValue.doubleValue() / 2);
                remover.setY(newValue.doubleValue() / 2);
            }));
            timeline.play();
        });
    }
}
