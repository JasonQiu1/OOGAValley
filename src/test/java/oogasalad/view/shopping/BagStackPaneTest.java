package oogasalad.view.shopping;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oogasalad.model.api.GameFactory;
import oogasalad.model.api.GameInterface;
import oogasalad.view.playing.PlayingPageView;
import oogasalad.view.shopping.components.ItemView;
import oogasalad.view.shopping.components.bagblock.BagStackPane;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class BagStackPaneTest extends DukeApplicationTest {

  private BagStackPane bagStackPane;
  private GameInterface game;

  @Before
  public void start(Stage stage) {
    game = new GameFactory().createGame();
  }

  @Test
  public void testInitializationNumber() {
    sleep(3000);
    assertEquals(game.getGameState().getBag().getItems().size(),
        bagStackPane.getChildren().size());
  }

  @Test
  public void testRemainNubmer(){

  }
}
