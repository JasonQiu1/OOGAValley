package oogasalad.model;

import java.io.IOException;
import java.util.Collections;
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

  @Override
  protected void initializeGameObjects() throws IOException {

  }
}

