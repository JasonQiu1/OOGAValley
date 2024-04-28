package oogasalad.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import oogasalad.model.api.GameInterface;
import oogasalad.model.gameplay.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Handles key events in the game
 */
public class GameKeyHandler implements EventHandler<KeyEvent> {

  private final Game game;
  private static final Logger LOG = LogManager.getLogger(GameKeyHandler.class);

  public GameKeyHandler(GameInterface game) {
    this.game = (Game) game;
  }

  @Override
  public void handle(KeyEvent event) {
    switch (event.getCode()) {
      case M:
        LOG.info("Activated money cheat code!");
        game.getEditableGameState().addMoney(1000);
    }
  }
}
