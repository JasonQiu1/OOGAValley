package oogasalad.view.editor.MapEditor.MapExtender;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class MapExtenderAbstract extends Group {

  private final Rectangle adder;
  private final Rectangle remover;

  public MapExtenderAbstract(EventHandler<MouseEvent> onActionAdd,
      EventHandler<MouseEvent> onActionRemove) {
    super();
    adder = new Rectangle();
    remover = new Rectangle();
    adder.setFill(Color.GREEN);
    remover.setFill(Color.RED);
    adder.setStroke(Color.BLACK);
    remover.setStroke(Color.BLACK);
    adder.setStrokeWidth(2);
    remover.setStrokeWidth(2);
    adder.setOnMouseClicked(onActionAdd);
    remover.setOnMouseClicked(onActionRemove);
    super.getChildren().addAll(adder, remover);
    super.setOpacity(0);

    super.setOnMouseEntered(e -> super.setOpacity(1));
    super.setOnMouseExited(e -> super.setOpacity(0));

    adder.setOnMouseEntered(e -> adder.setOpacity(.5));
    adder.setOnMouseExited(e -> adder.setOpacity(1));

    remover.setOnMouseEntered(e -> remover.setOpacity(.5));
    remover.setOnMouseExited(e -> remover.setOpacity(1));
  }

  protected Rectangle getAdder() {
    return adder;
  }

  protected Rectangle getRemover() {
    return remover;
  }

}
