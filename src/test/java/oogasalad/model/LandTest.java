package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.GameState;
import oogasalad.model.data.Properties;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameobject.Land;
import oogasalad.model.gameobject.Structure;
import oogasalad.model.gameplay.Game;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LandTest {
  private Land testingLand;
  private Properties testingLandProperties;
  private GameConfigurablesStore editableConfigurablesStore;
  private Map<String, Properties> allEditableConfigurables;

  @BeforeEach
  public void setUp() throws IOException {
    String fileName = "testWorld1.json";
    GameConfiguration gameConfiguration = GameConfiguration.of("TempGameConfiguration.json");
    editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    testingLandProperties = Properties.of("test/testingGrassLand.json");
    editableConfigurablesStore.getAllEditableConfigurables().put("grass_land", testingLandProperties);
    allEditableConfigurables.put("grass_land", testingLandProperties);
    GameObjectFactory factory = new GameObjectFactory();
    testingLand = (Land) factory.createNewGameObject("grass_land", new GameTime(1,1,1), new HashMap<>());
    gameConfiguration.save(fileName);
  }

  @Test
  public void testValidSeed() {
    assertTrue(testingLand.getIfItemCanBePlacedHere(new Item("wheat_seed")));
  }

  @Test
  public void testInvalidSeed() {
    assertFalse(testingLand.getIfItemCanBePlacedHere(new Item("invalid")));
  }

  @Test
  public void testGetStructureIdBasedOnPlantableItem() {
    assertEquals("wheat", testingLand.getStructureBasedOnItem(new Item("wheat_seed")));
  }
}
