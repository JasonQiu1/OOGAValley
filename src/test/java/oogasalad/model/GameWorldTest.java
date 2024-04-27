package oogasalad.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import oogasalad.model.gameobject.*;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.gameplay.BuildableTileMap;
import oogasalad.model.gameplay.GameWorld;
import oogasalad.model.api.ReadOnlyItem;

public class GameWorldTest extends TileTest {

  private BuildableTileMap gameWorld;
  private static final GameObjectFactory factory = new GameObjectFactory();

 @Override
 protected void initializeGameObjects() throws IOException {
   super.initializeGameObjects();
   gameWorld = new BuildableTileMap(5,5,1);
   gameWorld.setTileGameObject(getTestingLandProperties().getString("name"),
       1,1,0);
   gameWorld.setTileGameObject(getTestingStructureProperties().getString("name"),
       1,1,0);
   getTestingStructureProperties().update("updatable", "true");
   getTestingStructureProperties().update("updateTime", "1");
   addPropertiesToStore("wheat", "test/testingWheat.json");
 }



  @Test
  public void testUpdateGameWorld() {
    ReadOnlyGameTime gameTime = new GameTime(1, 30, 0);
    gameWorld.update(gameTime);
    gameWorld.update(gameTime);
    List<String> ids = gameWorld.getTileContents(1,1,0);
    List<String> expectedList = new ArrayList<>();
    expectedList.add(getTestingLandProperties().getString("name"));
    expectedList.add(getTestingStructureProperties().getString("updateTransformation"));
    assertTrue(gameWorld.getTileContents(2,2,0).isEmpty());
    assertEquals(expectedList, ids);
  }

  @Test
  public void testDimensionModification() {
    gameWorld.setWidth(10);
    assertEquals(10, gameWorld.getWidth(), "Width should be updated to 10");

    gameWorld.setHeight(10);
    assertEquals(10, gameWorld.getHeight(), "Height should be updated to 10");

    gameWorld.setDepth(2);
    assertEquals(2, gameWorld.getDepth(), "Depth should be updated to 2");
  }

  // Additional tests might be necessary to cover all use cases and features
}



