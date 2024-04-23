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
import oogasalad.model.data.Properties;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameObjectTest {

  private GameObject testingStructure;
  private Properties testingStructureProperties;
  private Properties tilledGrassProperties;
  private Properties differentThingProperties;
  private GameConfigurablesStore editableConfigurablesStore;
  private Map<String, Properties> allEditableConfigurables;

  @BeforeEach
  public void setUp() throws IOException {
    String fileName = "testWorld1.json";
    GameConfiguration gameConfiguration = GameConfiguration.of("TempGameConfiguration.json");
    editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    testingStructureProperties = Properties.of("test/testingGameObject.json");
    tilledGrassProperties = Properties.of("test/testingTilledGrass.json");
    differentThingProperties = Properties.of("test/testingDifferentThing.json");
    addPropertiestoConfigurableStore("grass_structure", testingStructureProperties);
    addPropertiestoConfigurableStore("tilled_grass", tilledGrassProperties);
    addPropertiestoConfigurableStore("differentThing", differentThingProperties);
    GameObjectFactory factory = new GameObjectFactory();
    testingStructure = factory.createNewGameObject("grass_structure", new GameTime(1,1,1), new HashMap<>());
    gameConfiguration.save(fileName);
  }

  private void addPropertiestoConfigurableStore(String id, Properties properties) {
    editableConfigurablesStore.getAllEditableConfigurables().put(id, properties);
    allEditableConfigurables.put(id, properties);
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
    testingStructure.interact(new Item("validItem"));
    assertEquals("grass_structure", testingStructure.getId());
    testingStructure.update(new GameTime(1,1,1));
    assertEquals("tilled_grass", testingStructure.getId());
  }
  @Test
  public void updateShouldHaveUpdated() {
    testingStructure.update(new GameTime(2, 2, 2));
    testingStructure.update(new GameTime(2, 2, 2));
    assertEquals("tilled_grass", testingStructure.getId());
  }

  @Test
  public void updateShouldNotHaveUpdatedDespiteValidUpdateDueToItUpdatingOnNextIteration() {
    testingStructure.update(new GameTime(2, 2, 2));
    assertEquals("grass_structure", testingStructure.getId());
  }

  @Test
  public void shouldBeExpired() {
    testingStructureProperties.update("expirable", "true");
    testingStructureProperties.update("updatable", "false");
    assertEquals("grass_structure", testingStructure.getId());
    assertFalse(testingStructure.checkAndUpdateExpired(new GameTime(2,2,2)));
    testingStructure.update(new GameTime(2,2,2));
    assertTrue(testingStructure.checkAndUpdateExpired(new GameTime(3,3,3)));
  }

  @Test
  public void shouldThrowIncorrectType() {
    tilledGrassProperties.update("type", "Land");
    testingStructure.update(new GameTime(2,2,2));
    assertThrows(IncorrectPropertyFileType.class, () -> testingStructure.update(new GameTime(2, 2, 2)));
  }

  @Test
  public void testingUpdateAfterUpdate() {
    assertEquals("tilled_grass", testingStructure.getId());
    testingStructure.update(new GameTime(2,2,3));
    testingStructure.update(new GameTime(2,2,3));
    assertEquals("tilled_grass", testingStructure.getId());
    testingStructure.update(new GameTime(2,2,5));
    assertEquals("tilled_grass", testingStructure.getId());
    testingStructure.update(new GameTime(2,2,5));
    assertEquals("differentThing", testingStructure.getId());
  }

  @Test
  public void expireBeforeUpdate() {
    testingStructureProperties.update("updateTime", "15");
    testingStructureProperties.update("expirable", "true");
    testingStructureProperties.update("expireTime", "10");
    testingStructure.update(new GameTime(1,1,1));
    assertFalse(testingStructure.checkAndUpdateExpired(new GameTime(1,1,9)));
    assertTrue(testingStructure.checkAndUpdateExpired(new GameTime(1,1,12)));
    testingStructure.update(new GameTime(1,1,14));
    testingStructure.update(new GameTime(1,1,14));
    assertEquals("grass_structure", testingStructure.getId());
  }
}