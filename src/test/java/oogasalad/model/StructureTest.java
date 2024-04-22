package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.GameState;
import oogasalad.model.data.Properties;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Structure;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StructureTest {
  private Structure testingStructure;

  @BeforeEach
  public void setUp() throws IOException {
    //    The file to save the gameWorld to
    String fileName = "testWorld1.json";
//    the id for the grass land
    String id = "grass_structure";
    GameConfiguration gameConfiguration = GameConfiguration.of("TempGameConfiguration.json");
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties property = new Properties();
    property.getProperties().put("image", "/img/grass.jpg");
    property.getProperties().put("updatable", "true");
    property.getProperties().put("updateTime", "10");
    property.getProperties().put("expirable", "false");
    property.getProperties().put("updateTransformation", "tilled_grass");
    Map<String, String> drops = new HashMap<>();
    drops.put("seed", "2");
    property.getMapProperties().put("dropsOnDestruction", drops);
    Map<String, String> interactTransform = new HashMap<>();
    interactTransform.put("validItem", "tilled_grass");
    property.getMapProperties().put("interactTransformations", interactTransform);
    allEditableConfigurables.put(id, property);
    GameState gameState = new GameState();
    testingStructure = new Structure(id, new GameTime(1,1,1));
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
