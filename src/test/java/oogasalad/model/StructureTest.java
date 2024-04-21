package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameobject.Structure;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StructureTest {

  private Structure testingStructure;

  @BeforeEach
  public void setUp() {
    GameObjectFactory g1 = new GameObjectFactory();
    GameTime time = new GameTime(0,0,0);
    testingStructure = (Structure) g1.createNewGameObject("wheat", time, new HashMap<>());
  }

  @Test
  public void createTest() {
    testingStructure.getItemsOnDestruction();
    assertEquals(1,1);
  }
}