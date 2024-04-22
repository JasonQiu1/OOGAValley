package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.GameState;
import oogasalad.model.data.Properties;
import oogasalad.model.gameobject.Item;
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
    testingStructure = new Structure(id, gameState.getEditableGameTime().copy());
    gameConfiguration.save(fileName);
  }


  @Test
  public void testImagePathCorrect() {
    assertEquals("/img/grass.jpg", testingStructure.getImagePath());
  }

  @Test
  public void retrieveStructureDropsCorrectly() {
    assertTrue(testingStructure.getItemsOnDestruction().containsKey("seed"));
  }

  @Test
  public void testStructureInteractionInvalid() {
    assertFalse(testingStructure.interactionValid(new Item("thing")));
  }

  @Test
  public void testStructureInteractionValid() {
    String id = "tilled_grass";
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties property = new Properties();
    allEditableConfigurables.put(id, property);
    assertTrue(testingStructure.interactionValid(new Item("validItem")));
  }

  @Test
  public void retrieveStructureDropsAmountCorrectly() {
    assertEquals(2, testingStructure.getItemsOnDestruction().get("seed"));
  }

  @Test
  public void verifyId() {
    assertEquals("grass_structure", testingStructure.getId());
  }

  @Test
  public void updateNotEnoughTimeWillNotUpdate() {
    testingStructure.update(new GameTime(1, 1, 1));
    testingStructure.update(new GameTime(1, 1, 1));
    assertEquals("grass_structure", testingStructure.getId());
  }

  @Test
  public void updateShouldHaveUpdated() {
    String id = "tilled_grass";
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties property = new Properties();
    property.getProperties().put("type", "Structure");
    property.getProperties().put("expirable", "false");
    allEditableConfigurables.put(id, property);
    testingStructure.update(new GameTime(2, 2, 2));
    testingStructure.update(new GameTime(2, 2, 2));
    assertEquals("tilled_grass", testingStructure.getId());
  }

  @Test
  public void updateShouldNotHaveUpdatedDespiteValidUpdateDueToItUpdatingOnNextIteration() {
    String id = "tilled_grass";
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties property = new Properties();
    property.getProperties().put("type", "Structure");
    property.getProperties().put("expirable", "false");
    allEditableConfigurables.put(id, property);
    testingStructure.update(new GameTime(2, 2, 2));
    assertEquals("grass_structure", testingStructure.getId());
  }


}