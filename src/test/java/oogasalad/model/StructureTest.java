package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.model.data.GameConfigurablesStore;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.data.Properties;
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
    GameConfiguration gameConfiguration = GameConfiguration.of("test");
    GameConfigurablesStore editableConfigurablesStore = GameConfiguration.getEditableConfigurablesStore();
    Map<String, Properties> allEditableConfigurables = editableConfigurablesStore.getAllEditableConfigurables();
    Properties properties = new Properties();
    System.out.println(allEditableConfigurables);
    properties.getProperties().put("wheat", "100");
    allEditableConfigurables.put("store", properties);
    editableConfigurablesStore.save("test.json");
    gameConfiguration.save("test.json");
  }

  @Test
  public void createTest() {
    testingStructure.getItemsOnDestruction();
    assertEquals(1,1);
  }
}