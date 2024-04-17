package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import oogasalad.model.data.GameConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameConfigurationTest {

  static Logger LOG = LogManager.getLogger(GameConfigurationTest.class);

  @Test
  void defaultRules() {
    GameConfiguration config = new GameConfiguration();
    boolean doEnergy =
        Assertions.assertDoesNotThrow(() -> config.getRules().getBoolean("doEnergy"));
    LOG.debug(config.getRules().getCopyOfProperties());
    assertTrue(doEnergy);
    //config.getRules().update("doEnergy", true)
  }
}