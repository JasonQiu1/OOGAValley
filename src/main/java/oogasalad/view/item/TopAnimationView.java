package oogasalad.view.item;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class TopAnimationView extends StackPane {

  private LandView landView;
  private ItemView itemView;
  private double width;
  private double height;

  public TopAnimationView(LandView landView, ItemView itemView, double width, double height) {
    this.landView = landView;
    this.itemView = itemView;
    this.width = width;
    this.height = height;
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
