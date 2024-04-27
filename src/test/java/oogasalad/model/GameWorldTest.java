package oogasalad.model;

import java.io.IOException;
import java.util.HashMap;
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

  @BeforeEach
  public void setup() {
    gameWorld = new BuildableTileMap(5, 5, 1);
    gameWorld.setTileGameObject();
  }



  @Test
  public void testUpdateGameWorld() {
    ReadOnlyGameTime gameTime = new GameTime(1, 30, 0);
    gameWorld.update(gameTime);

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



