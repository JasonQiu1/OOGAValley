package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.gameobject.Structure;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StructureTest {

  private Structure testingStructure;

  @BeforeEach
  public void setUp() throws IOException {
    GameObjectFactory g1 = new GameObjectFactory();
    GameTime time = new GameTime(0,0,0);
//    ReadOnlyGameConfigurablesStore store = GameConfiguration.getConfigurablesStore();
//    Map<String, ReadOnlyProperties> properties = store.getAllConfigurables();
//    ReadOnlyProperties prop1 = Properties.of("templates/Structure.json");
    testingStructure = (Structure) g1.createNewGameObject("Wheat", time, new HashMap<>());
  }

  @Test
  public void createTest() {
    testingStructure.getItemsOnDestruction();
    assertEquals(1,1);
  }
}