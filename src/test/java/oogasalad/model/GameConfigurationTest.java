package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Map;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.Properties;
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

  @Test
  void addItemToGameStore() throws IOException {
    GameConfiguration gameConfiguration = GameConfiguration.of("test");
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties properties = new Properties();
    System.out.println(allEditableConfigurables);
    properties.getProperties().put("wheat", "100");
    allEditableConfigurables.put("store", properties);
    editableConfigurablesStore.save("test.json");
    gameConfiguration.save("test.json");
  }

  @Test
  void testGameConfiguration() throws IOException {
    String fileName = "test.json";
    GameConfiguration gameConfiguration = GameConfiguration.of(fileName);
    GameConfigurablesStore gameConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    System.out.println(gameConfigurablesStore.getAllEditableConfigurables());

  }
}