package oogasalad.view.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oogasalad.Main;
import oogasalad.model.shop.Bag;
import oogasalad.model.shop.BagItemModel;
import oogasalad.model.shop.ItemType;
import oogasalad.view.shopping.components.bagblock.BagGridPane;
import oogasalad.view.shopping.components.bagblock.BagItemVbox;
import oogasalad.view.shopping.components.bagblock.RemainNumStackPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class BagComponentsTest extends ApplicationTest {

  private Bag mockBag;

  @BeforeAll
  public static void setUpClass() throws Exception {
    ApplicationTest.launch(Main.class);
  }

  @BeforeEach
  public void setUp() {
    mockBag = mock(Bag.class);
    when(mockBag.getItemMap()).thenReturn(
        new HashMap<BagItemModel, Integer>() {
          {
            put(new BagItemModel("img/wheat.png", ItemType.SELL), 1);
            put(new BagItemModel("img/panda.png", ItemType.SELL), 2);
          }
        });
  }

  @Test
  public void testBagGridPaneLayout() {
    BagGridPane gridPane = new BagGridPane(mockBag);
    assertEquals("Expected child count", 2, gridPane.getChildren().size());
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
