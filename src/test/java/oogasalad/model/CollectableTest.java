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
import oogasalad.model.gameobject.Collectable;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameobject.Land;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollectableTest {
  private Collectable testingCollectable;

  @BeforeEach
  public void setUp() throws IOException {
    //    The file to save the gameWorld to
    String fileName = "testWorld1.json";
//    the id for the grass land
    String id = "grass_land";
    GameConfiguration gameConfiguration = GameConfiguration.of("TempGameConfiguration.json");
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties property = new Properties();
    property.getProperties().put("image", "/img/grass.jpg");
    property.getProperties().put("updatable", "true");
    property.getProperties().put("updateTime", "10");
    property.getProperties().put("expirable", "false");
    property.getProperties().put("updateTransformation", "tilled_grass");
    Map<String, String> seeds = new HashMap<>();
    seeds.put("wheat_seed", "wheat");
    property.getMapProperties().put("plantableSeeds", seeds);
    Map<String, String> interactTransform = new HashMap<>();
    interactTransform.put("validItem", "tilled_grass");
    property.getMapProperties().put("interactTransformations", interactTransform);
    allEditableConfigurables.put(id, property);
    GameState gameState = new GameState();
    Map<String, Integer> itemsOnCollection = new HashMap<>();
    itemsOnCollection.put("hoe", 2);
    testingCollectable = new Collectable(id, new GameTime(1,1,1), itemsOnCollection);
    gameConfiguration.save(fileName);
  }

  @Test
  public void testingGettingItemsOnCollection() {
    assertEquals(2, (int) testingCollectable.getItemsOnCollection().get("hoe"));
  }

  @Test
  public void testingShouldICollectWhenIShould() {
    testingCollectable.interact(new Item("validItem"));
    assertTrue(testingCollectable.shouldICollect());
  }

  @Test
  public void testShouldICollectWhenIShouldNot() {
    testingCollectable.interact(new Item("InvalidItem"));
    assertFalse(testingCollectable.shouldICollect());
  }
}
