package oogasalad.model.gameObjectFactories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import oogasalad.model.api.ReadOnlyGameTime;
import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.api.exception.GameObjectFactoryInstantiationFailure;
import oogasalad.model.api.exception.InvalidGameObjectType;
import oogasalad.model.api.exception.KeyNotFoundException;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameplay.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * Factory class for creating GameObject instances dynamically based on the type specified in
 * properties. This factory uses the Reflections library to discover all GameObjectCreator
 * implementations within its package, and automatically registers them for use in creating specific
 * types of GameObjects.
 */
public class GameObjectFactory {

  private final Map<String, GameObjectCreator> creators = new HashMap<>();

  /**
   * Constructs a GameObjectFactory and discovers all available GameObjectCreator types in its
   * package.
   */
  public GameObjectFactory() throws GameObjectFactoryInstantiationFailure {
    discoverCreators();
  }

  /**
   * Discovers and registers all classes implementing the GameObjectCreator interface in the current
   * package. Each creator is indexed by a key derived from its class name.
   *
   * @throws GameObjectFactoryInstantiationFailure if any creator could not be instantiated.
   */
  private void discoverCreators() throws GameObjectFactoryInstantiationFailure {
    String packageName = this.getClass().getPackage().getName();
    Reflections reflections = new Reflections(
        new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(packageName)));
    Set<Class<? extends GameObjectCreator>> classes =
        reflections.getSubTypesOf(GameObjectCreator.class);
    for (Class<? extends GameObjectCreator> clazz : classes) {
      try {
        GameObjectCreator creator = clazz.getDeclaredConstructor().newInstance();
        String typeName = clazz.getSimpleName().toLowerCase().replace("creator", "");
        creators.put(typeName, creator);
      } catch (Exception e) {
        throw new GameObjectFactoryInstantiationFailure("Unable to Create GameObjectCreators");
      }
    }
  }

  /**
   * Creates a new GameObject based on the type specified in the provided ReadOnlyProperties.
   *
   * @param id               The id of the gameObject to be created.
   * @param creationTime     The game time at which the GameObject is being created.
   * @param additionalParams A map of additional parameters required for creating specific types of
   *                         GameObjects.
   * @return A new instance of a GameObject.
   * @throws InvalidGameObjectType if the specified type is not recognized or supported.
   */
  public GameObject createNewGameObject(String id, ReadOnlyGameTime creationTime,
      Map<String, Integer> additionalParams) throws InvalidGameObjectType {
    String type;
    try {
      ReadOnlyProperties properties =
          GameConfiguration.getConfigurablesStore().getConfigurableProperties(id);
      type = properties.getString("type").toLowerCase();
    } catch (KeyNotFoundException e) {
      LOG.error("Couldn't find '" + id + "' in configurables store or invalid type!");
      throw new RuntimeException(e);
    }
    GameObjectCreator creator = creators.get(type);
    if (creator == null) {
      String errorMessage = "Could not create a gameObject of type: " + type;
      LOG.error(errorMessage);
      throw new InvalidGameObjectType(errorMessage);
    }
    return creator.create(id, creationTime, additionalParams);
  }

  /**
   * @return A list of the class names of all creators stored in the Factory.
   */
  public List<String> getListOfCreators() {
    return creators.values().stream().map(creator -> creator.getClass().getSimpleName())
        .collect(Collectors.toList());
  }

  private static final Logger LOG = LogManager.getLogger(GameObjectFactory.class);
}






