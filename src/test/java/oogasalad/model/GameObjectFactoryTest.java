package oogasalad.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import org.junit.jupiter.api.Test;

public class GameObjectFactoryTest {

  private final String[] expectedCreators = {"CollectableCreator", "LandCreator", "StructureCreator"};
  @Test
  public void ensureDiscoverCreatorsCorrectlyIdentifiesAllCreators() {
    GameObjectFactory factory = new GameObjectFactory();
    List<String> actualCreators = factory.getListOfCreators();
    for (String creator : expectedCreators) {
      assertTrue(actualCreators.contains(creator), "Factory should contain: " + creator);
    }
  }
}
