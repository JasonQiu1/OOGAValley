package oogasalad.model;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.model.data.Properties;
import oogasalad.model.gameobject.Tile;
import oogasalad.model.gameobject.Collectable;
import oogasalad.model.gameobject.Structure;
import oogasalad.model.gameobject.Land;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest extends BaseGameObjectTest {

  private Land tileLand;
  private Structure tileStructure;
  private Collectable tileCollectable;
  private Properties testingStructureProperties;
  private Properties testingLandProperties;
  private Properties testingCollectableProperties;
  private Tile tileToTest;

  @Override
  protected void initializeGameObjects() throws IOException {
    testingStructureProperties = Properties.of("test/testingTileStructure.json");
    testingLandProperties = Properties.of("test/testingTileLand.json");
    testingCollectableProperties = Properties.of("test/testingTileCollectable.json");
    addPropertiesToStoreWithAlreadyCreatedProperties
        (testingStructureProperties.getString("name"), testingStructureProperties);
    addPropertiesToStoreWithAlreadyCreatedProperties
        (testingLandProperties.getString("name"), testingLandProperties);
    addPropertiesToStoreWithAlreadyCreatedProperties
        (testingCollectableProperties.getString("name"), testingCollectableProperties);

    tileStructure = (Structure) getFactory()
        .createNewGameObject(testingStructureProperties.getString("name"),
            new GameTime(1,1,1), new HashMap<>());
    tileLand = (Land) getFactory()
        .createNewGameObject(testingLandProperties.getString("name"),
            new GameTime(1,1,1), new HashMap<>());
    Map<String, Integer> collectableItems = new HashMap<>();
    collectableItems.put("axe", 2);
    tileCollectable = (Collectable) getFactory()
        .createNewGameObject(testingCollectableProperties.getString("name"),
            new GameTime(1,1,1), collectableItems);
    tileToTest = new Tile();
    tileToTest.setCollectable(tileCollectable);
    tileToTest.setLand(tileLand);
    tileToTest.setStructure(tileStructure);
  }
}

