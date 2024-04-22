package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Map;
import oogasalad.model.api.ReadOnlyProperties;
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
  }

  @Test
  void addItemToGameStore() throws IOException {
    GameConfiguration gameConfiguration = GameConfiguration.of("TempGameConfiguration.json");
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
    System.out.println(gameConfigurablesStore.getAllConfigurables());
    assertEquals(gameConfigurablesStore.getAllConfigurables().size(), 1);
  }

  @Test
  void addProperties() throws IOException {
//    Create Game config and game config store from a file name
    String fileName = "test.json";
    GameConfiguration gameConfiguration = GameConfiguration.of(fileName);
    GameConfigurablesStore gameConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
//    get the map of the properties <id, properties>
    Map<String, Properties> allConfigurables = gameConfigurablesStore.getAllEditableConfigurables();
//    Put id and properties inside
    Properties properties = new Properties();
    allConfigurables.put("test", properties);
//    Get the properties
    ReadOnlyProperties test = gameConfigurablesStore.getConfigurableProperties("test");
    assertNotNull(test);
  }

  @Test
  void initializeBlock() {

  }
}