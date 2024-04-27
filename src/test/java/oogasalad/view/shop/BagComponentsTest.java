package oogasalad.view.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oogasalad.Main;
import oogasalad.model.api.GameFactory;
import oogasalad.model.api.GameInterface;
import oogasalad.view.shopping.components.bagblock.BagItemVbox;
import oogasalad.view.shopping.components.bagblock.RemainNumStackPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class BagComponentsTest extends ApplicationTest {

  private GameInterface game = new GameFactory().createGame();

  @BeforeAll
  public static void setUpClass() throws Exception {
    ApplicationTest.launch(Main.class);
  }


  @Test
  public void testBagItemVboxDisplaysCorrectData() {
    ImageView realImage = new ImageView(new Image("img/wheat.png"));
    RemainNumStackPane remainPane = new RemainNumStackPane(5);

    BagItemVbox vbox = new BagItemVbox(realImage, remainPane);

    assertEquals("Children count should be 2", 2, vbox.getChildren().size());
    assertTrue("First child should be ImageView", vbox.getChildren().get(0) instanceof ImageView);
    assertTrue("Second child should be RemainNumStackPane",
        vbox.getChildren().get(1) instanceof RemainNumStackPane);

    RemainNumStackPane testRemainPane = (RemainNumStackPane) vbox.getChildren().get(1);
    Label label = (Label) testRemainPane.getChildren().get(1);
    assertEquals("Label should display the remaining number", "5", label.getText());
  }


}
