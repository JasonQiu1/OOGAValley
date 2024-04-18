package oogasalad.view.play;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import oogasalad.view.item.Pile;
import oogasalad.view.playing.PlayingPageView;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PlayingPageTest extends DukeApplicationTest {

  private Stage stage;
  private PlayingPageView playingPageView;

  public void start(Stage stage) {
    this.stage = stage;
    playingPageView = new PlayingPageView(stage);
    playingPageView.start();
  }

  @Test
  public void testShoppingButton() {
    Scene scene = stage.getScene();
    Button shoppingButton = (javafx.scene.control.Button) lookup("#shopButton").queryButton();
    clickOn(shoppingButton);
    sleep(500);
    assertNotEquals(stage.getScene(), scene);
  }

  @Test
  public void testSelected(){
    // fixed
    StackPane panda = (StackPane) lookup("#Panda").query();
    StackPane hoe = (StackPane) lookup("#Hoe").query();
    clickOn(panda);
    sleep(1000);
    Rectangle rec1 = (Rectangle) panda.getChildren().get(1);
    assertNotEquals(0, rec1.getOpacity(), 0.0);
    clickOn(hoe);
    sleep(1000);
    Rectangle rec2 = (Rectangle) hoe.getChildren().get(1);
    assertNotEquals(0, rec2.getOpacity(), 0.0);
    assertEquals(0, rec1.getOpacity(), 0.0);
  }

  @Test
  public void testGrowAndHarvest(){
    StackPane panda = (StackPane) lookup("#Panda").query();
    clickOn(panda);
    sleep(1000);
    StackPane pile = (StackPane) lookup("#pile35").query();
    assertNull(((Pile) pile).getPlantView());
    clickOn(pile);
    sleep(1000);
    assertNotNull(((Pile) pile).getPlantView());

    sleep(5000);
    StackPane hoe = (StackPane) lookup("#Hoe").query();
    clickOn(hoe);
    sleep(1000);
    clickOn(pile);
    sleep(1000);
    assertNull(((Pile) pile).getPlantView());
    sleep(3000);

  }

}
