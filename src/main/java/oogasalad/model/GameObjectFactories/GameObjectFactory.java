package oogasalad.model.GameObjectFactories;

import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.api.exception.GameObjectFactoryInstantiationFailure;
import oogasalad.model.api.exception.InvalidGameObjectType;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameplay.GameTime;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.ClasspathHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Factory class for creating GameObject instances dynamically based on the type specified in properties.
 * This factory uses the Reflections library to discover all GameObjectCreator implementations within its package,
 * and automatically registers them for use in creating specific types of GameObjects.
 */
public class GameObjectFactory {
  private final Map<String, GameObjectCreator> creators = new HashMap<>();

  /**
   * Constructs a GameObjectFactory and discovers all available GameObjectCreator types in its package.
   */
  public GameObjectFactory() {
    discoverCreators();
  }

  /**
   * Discovers and registers all classes implementing the GameObjectCreator interface in the current package.
   * Each creator is indexed by a key derived from its class name.
   * @throws GameObjectFactoryInstantiationFailure if any creator could not be instantiated.
   */
  private void discoverCreators() {
    String packageName = this.getClass().getPackage().getName();
    Reflections reflections = new Reflections(new ConfigurationBuilder()
        .setUrls(ClasspathHelper.forPackage(packageName)));
    Set<Class<? extends GameObjectCreator>> classes = reflections.getSubTypesOf(GameObjectCreator.class);
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
   * @param properties The properties defining the type of GameObject and other parameters.
   * @param creationTime The game time at which the GameObject is being created.
   * @param additionalParams A map of additional parameters required for creating specific types of GameObjects.
   * @return A new instance of a GameObject.
   * @throws InvalidGameObjectType if the specified type is not recognized or supported.
   */
  public GameObject createNewGameObject(String id, GameTime creationTime,
      Map<String, Integer> additionalParams) {
    ReadOnlyProperties properties = GameConfiguration.getConfigurablesStore.getConfigurable(id);
    String type = properties.getString("type").toLowerCase();
    GameObjectCreator creator = creators.get(type);
    if (creator == null) {
      throw new InvalidGameObjectType("Could not create a gameObject of type: " + type);
    }
    return creator.create(properties, creationTime, additionalParams);
  }
}






