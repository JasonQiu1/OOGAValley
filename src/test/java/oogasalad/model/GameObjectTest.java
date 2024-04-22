package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.api.exception.IncorrectPropertyFileType;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.GameState;
import oogasalad.model.data.Properties;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameobject.Structure;
import oogasalad.model.gameplay.Game;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameObjectTest {

  private GameObject testingStructure;

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
  public void testImagePathCorrect() {
    assertEquals("/img/grass.jpg", testingStructure.getImagePath());
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
  public void interactShouldLeadToNewGameObject() {
    createNextGameObject("tilled_grass", "false", "Structure", "true", "10");
    testingStructure.interact(new Item("validItem"));
    assertEquals("grass_structure", testingStructure.getId());
    testingStructure.update(new GameTime(1,1,1));
    assertEquals("tilled_grass", testingStructure.getId());
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
    createNextGameObject("tilled_grass", "false", "Structure", "true", "10");
    testingStructure.update(new GameTime(2, 2, 2));
    assertEquals("grass_structure", testingStructure.getId());
  }

  private void createNextGameObject(String id, String expirable, String type, String updatable, String expireTime) {
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties property = new Properties();
    property.getProperties().put("type", type);
    property.getProperties().put("expirable", expirable);
    property.getProperties().put("updatable", updatable);
    property.getProperties().put("expireTime", expireTime);
    allEditableConfigurables.put(id, property);
  }
  private void createNextGameObjectFromTestingStructure() {
    createNextGameObject("tilled_grass", "true", "Structure", "false", "10");
    testingStructure.update(new GameTime(2, 2, 2));
    testingStructure.update(new GameTime(2, 2, 2));
  }
  @Test
  public void shouldBeExpired() {
    createNextGameObjectFromTestingStructure();
    assertEquals("tilled_grass", testingStructure.getId());
    assertFalse(testingStructure.checkAndUpdateExpired(new GameTime(2,2,2)));
    testingStructure.update(new GameTime(2,2,2));
    assertTrue(testingStructure.checkAndUpdateExpired(new GameTime(3,3,3)));
  }

  @Test
  public void shouldThrowIncorrectType() {
    createNextGameObject("tilled_grass", "false", "Land", "false", "10");
    testingStructure.update(new GameTime(2,2,2));
    assertThrows(IncorrectPropertyFileType.class, () -> testingStructure.update(new GameTime(2, 2, 2)));
  }
}