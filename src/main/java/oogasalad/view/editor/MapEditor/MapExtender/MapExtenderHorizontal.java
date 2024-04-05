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
        adder.setWidth(bm.getGridPane().getWidth());
        remover.setWidth(bm.getGridPane().getWidth());



        bm.getGridPaneProperty().addListener((observable, oldValue, newValue) -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
                adder.setWidth(newValue.getWidth() / 2);
                adder.setX(newValue.getWidth() / 2);
                remover.setWidth(newValue.getWidth() / 2);
            }));
            timeline.play();
        });

        bm.getGridPane().widthProperty().addListener((observable, oldValue, newValue) -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                adder.setWidth(newValue.doubleValue() / 2);
                adder.setX(newValue.doubleValue() / 2);
                remover.setWidth(newValue.doubleValue() / 2);
            }));
            timeline.play();
        });

    }
}