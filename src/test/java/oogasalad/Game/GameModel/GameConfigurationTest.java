package oogasalad.Game.GameModel;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class GameConfigurationTest {
  static Logger LOG = LogManager.getLogger(GameConfigurationTest.class);
  @Test
  void defaultRules() {
    GameConfiguration config = new GameConfiguration();
    boolean doEnergy = assertDoesNotThrow(() -> config.getRules().getBoolean("doEnergy"));
    LOG.debug(config.getRules().getCopyOfProperties());
    assertTrue(doEnergy);
  }
}