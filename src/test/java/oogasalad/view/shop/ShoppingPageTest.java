package oogasalad.view.shop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import oogasalad.model.shop.Bag;
import oogasalad.model.shop.Shop;
import oogasalad.view.playing.component.Money;
import oogasalad.view.shopping.ShoppingView;
import oogasalad.view.shopping.components.top.BackButton;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class ShoppingPageTest extends DukeApplicationTest {

  private Stage stage;
  private BackButton backButton;
  private ShoppingView shoppingView;
  private Scene scene;
  private Scene prev_scene;

  private Money money = new Money(100);


  @Override
  public void start(Stage stage) {
    this.stage = stage;
    prev_scene = new Scene(new javafx.scene.layout.StackPane(), 800, 800);
    this.stage.setScene(prev_scene);
    this.shoppingView = new ShoppingView(new Shop(money), new Bag(), this.stage, prev_scene, money);
    scene = new Scene(shoppingView.getScene());
    this.stage.setScene(scene);
    this.stage.getScene().getStylesheets().add("styles.css");
    this.stage.show();
    this.backButton = (BackButton) lookup("#backButton").queryButton();
  }

  @Test
  public void testBackButton() {
    clickOn(backButton);
    assertFalse(stage.getScene().equals(shoppingView.getPreviousScene()));
  }

  @Test
  public void testSellButton() {
    Button sellButton = (javafx.scene.control.Button) lookup("#sellButton").queryButton();
    clickOn(sellButton);
    sleep(1000);
    Button yesButton = (javafx.scene.control.Button) lookup("#yes-button").queryButton();
    clickOn(yesButton);
    sleep(1000);

  }

  @Test
  public void testPageChangeButton() {
    Button leftButton = (javafx.scene.control.Button) lookup(
        "#left-page-change-button").queryButton();
    assertTrue(leftButton.isDisabled());
    clickOn(leftButton);
    sleep(1000);
    Button rightButton = (javafx.scene.control.Button) lookup(
        "#right-page-change-button").queryButton();
    clickOn(rightButton);
    sleep(1000);
    assertTrue(rightButton.isDisabled());
    assertFalse(leftButton.isDisabled());
    clickOn(leftButton);
    sleep(1000);
    assertFalse(rightButton.isDisabled());
  }
}

