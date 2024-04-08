package oogasalad.view.item;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * This class is responsible for creating an animation that will be displayed on the top of the
 * screen when an item is collected. This class is dependent on the ItemView class.
 */
public class TopAnimationView extends StackPane {

  private final ItemView itemView;

  public TopAnimationView(ItemView itemView, double width, double height) {
    this.itemView = itemView;
    this.setPrefSize(width, height);
    this.setPickOnBounds(false);
  }

  public void collectItemAnimation(Item item, double startX, double startY, double endX,
      double endY, double speed) {
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(speed),
        item.getImageView());
    translateTransition.setFromX(startX);
    translateTransition.setFromY(startY);
    translateTransition.setToX(endX);
    translateTransition.setToY(endY);
    translateTransition.setInterpolator(Interpolator.EASE_BOTH);
    translateTransition.play();

    translateTransition.setOnFinished(event -> {
      this.getChildren().remove(0);
      itemView.addItem(item);
    });

    this.getChildren().add(item.getImageView());

  }


}
