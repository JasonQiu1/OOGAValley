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

  private final BagItemView bagItemView;

  /**
   * Constructor for the TopAnimationView class.
   *
   * @param bagItemView the itemView that the animation will be displayed on
   * @param width    the width of the animation
   * @param height   the height of the animation
   */
  public TopAnimationView(BagItemView bagItemView, double width, double height) {
    this.bagItemView = bagItemView;
    this.setPrefSize(width, height);
    this.setPickOnBounds(false);
  }

  /**
   * This method is responsible for creating an animation that will be displayed on the top of the
   * screen when an item is collected.
   *
   * @param bagItem   the item that is collected
   * @param startX the x-coordinate of the start of the animation
   * @param startY the y-coordinate of the start of the animation
   * @param endX   the x-coordinate of the end of the animation
   * @param endY   the y-coordinate of the end of the animation
   * @param speed  the speed of the animation
   */
  public void collectItemAnimation(BagItem bagItem, double startX, double startY, double endX,
      double endY, double speed) {
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(speed),
        bagItem.getImageView());
    translateTransition.setFromX(startX);
    translateTransition.setFromY(startY);
    translateTransition.setToX(endX);
    translateTransition.setToY(endY);
    translateTransition.setInterpolator(Interpolator.EASE_BOTH);
    translateTransition.play();

    translateTransition.setOnFinished(event -> {
      this.getChildren().remove(0);
      bagItemView.addItem(bagItem);
    });

    this.getChildren().add(bagItem.getImageView());

  }


}
