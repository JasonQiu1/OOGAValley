package oogasalad.view.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import oogasalad.model.api.GameFactory;
import oogasalad.model.api.GameInterface;
import oogasalad.view.playing.component.Money;
import oogasalad.view.shopping.ShoppingView;
import oogasalad.view.shopping.components.shopblock.PriceStackPane;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import util.DukeApplicationTest;

public class ShoppingPageTest extends ApplicationTest {

  private Stage stage;
  private Button backButton;
  private ShoppingView shoppingView;
  private Scene scene;
  private Scene prev_scene;

  private Money money = new Money(100);
  private GameInterface game = new GameFactory().createGame();

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    prev_scene = new Scene(new javafx.scene.layout.StackPane(), 800, 800);
    this.stage.setScene(prev_scene);

    this.shoppingView = new ShoppingView(game, this.stage, prev_scene, money, null);
    scene = new Scene(shoppingView.getScene());
    this.stage.setScene(scene);
    this.stage.getScene().getStylesheets().add("styles.css");
    this.stage.show();
    this.backButton = lookup("#backButton").queryButton();
  }



  @Test
  public void testPriceLabelContent() {
    PriceStackPane pane = new PriceStackPane(9.99);
    Label label = (Label) pane.getChildren().get(1);
    assertEquals("9.99", label.getText());
  }

  @Test
  public void testGridPopulation() {
    PriceStackPane pane = new PriceStackPane(9.99);
    assertEquals(2, pane.getChildren().size());
  }

}

