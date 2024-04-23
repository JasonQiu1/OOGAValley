package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.GameState;
import oogasalad.model.data.Properties;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Structure;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StructureTest {
  private Structure testingStructure;
  private Properties testingStructureProperties;
  private GameConfigurablesStore editableConfigurablesStore;
  private Map<String, Properties> allEditableConfigurables;

  @BeforeEach
  public void setUp() throws IOException {
    String fileName = "testWorld1.json";
    GameConfiguration gameConfiguration = GameConfiguration.of("TempGameConfiguration.json");
    editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    testingStructureProperties = Properties.of("test/testingGameObject.json");
    editableConfigurablesStore.getAllEditableConfigurables().put("grass_structure", testingStructureProperties);
    allEditableConfigurables.put("grass_structure", testingStructureProperties);
    GameObjectFactory factory = new GameObjectFactory();
    testingStructure = (Structure) factory.createNewGameObject("grass_structure", new GameTime(1,1,1), new HashMap<>());
    gameConfiguration.save(fileName);
  }

  @Test
  public void retrieveStructureDropsCorrectly() {
    assertTrue(testingStructure.getItemsOnDestruction().containsKey("seed"));
  }

  @Test
  public void retrieveStructureDropsAmountCorrectly() {
    assertEquals(2, testingStructure.getItemsOnDestruction().get("seed"));
  }

}
