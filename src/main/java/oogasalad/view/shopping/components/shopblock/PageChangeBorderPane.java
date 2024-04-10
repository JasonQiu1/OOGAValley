package oogasalad.view.shopping.components.shopblock;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import oogasalad.view.shopping.Utils;
import oogasalad.view.shopping.components.other.LeftPageChangeButton;
import oogasalad.view.shopping.components.other.RightPageChangeButton;

public class PageChangeBorderPane extends BorderPane {

  private LeftPageChangeButton leftButton;
  private RightPageChangeButton rightButton;

  public PageChangeBorderPane() {
    super();
    initialize();
    this.setPickOnBounds(false);
  }

  public void initialize() {
    leftButton = new LeftPageChangeButton();
    rightButton = new RightPageChangeButton();
    setAlignment(leftButton, Pos.CENTER_LEFT);
    setAlignment(rightButton, Pos.CENTER_RIGHT);
    setMargin(leftButton, new Insets(0, 0, 0, 10));
    setMargin(rightButton, new Insets(0, 10, 0, 0));
    setLeft(leftButton);
    setRight(rightButton);
    setPrefSize(Utils.pageChangeBorderPaneWidth, Utils.pageChangeBorderPaneHeight);
  }

  public LeftPageChangeButton getLeftButton() {
    return leftButton;
  }

  public RightPageChangeButton getRightButton() {
    return rightButton;
  }
}

