package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.gameobject.Collectable;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.Test;

public class CollectableTest extends BaseGameObjectTest {

  private Collectable testingCollectable;

  @Override
  protected void initializeGameObjects() throws IOException {
    addPropertiesToStore("grass_collectable", "test/testingGrassCollectable.json");
    Map<String, Integer> itemsOnCollection = new HashMap<>();
    itemsOnCollection.put("hoe", 2);
    testingCollectable = (Collectable) getFactory().createNewGameObject("grass_collectable", new GameTime(1,1,1), itemsOnCollection);
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

