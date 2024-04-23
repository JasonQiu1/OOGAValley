package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.Properties;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameobject.Collectable;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameobject.Land;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollectableTest {
  private Collectable testingCollectable;
  private Properties testingCollectableProperties;
  private GameConfigurablesStore editableConfigurablesStore;
  private Map<String, Properties> allEditableConfigurables;

  @BeforeEach
  public void setUp() throws IOException {
    String fileName = "testWorld1.json";
    GameConfiguration gameConfiguration = GameConfiguration.of("TempGameConfiguration.json");
    editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    testingCollectableProperties = Properties.of("test/testingGrassCollectable.json");
    editableConfigurablesStore.getAllEditableConfigurables().put("grass_collectable",
        testingCollectableProperties);
    allEditableConfigurables.put("grass_collectable", testingCollectableProperties);
    GameObjectFactory factory = new GameObjectFactory();
    Map<String, Integer> itemsOnCollection = new HashMap<>();
    itemsOnCollection.put("hoe", 2);
    testingCollectable = (Collectable) factory.createNewGameObject("grass_collectable", new GameTime(1,1,1), itemsOnCollection);
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
