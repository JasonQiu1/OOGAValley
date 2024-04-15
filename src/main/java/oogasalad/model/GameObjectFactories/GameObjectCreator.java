package oogasalad.model.GameObjectFactories;

import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.gameplay.GameTime;
import oogasalad.model.gameobject.GameObject;
import java.util.Map;

/**
 * Interface for creating GameObject instances. Implementations of this interface
 * define how different types of GameObjects are created based on provided properties,
 * game time, and additional parameters.
 */
public interface GameObjectCreator {

  /**
   * Creates a GameObject with specified properties, creation time, and additional parameters.
   *
   * @param properties Properties that define characteristics and behavior of the GameObject.
   *                   These properties should include type-specific settings and attributes necessary
   *                   for creating and initializing the GameObject.
   * @param creationTime The game time at which the GameObject is being created. This can be used to
   *                     set initial states or effects that depend on the game's timing or logic phases.
   * @param additionalParams A map containing additional type-specific parameters that might be required
   *                         for creating the GameObject.
   * @return The newly created GameObject, fully initialized and ready to be used within the game environment.
   */
  GameObject create(ReadOnlyProperties properties, GameTime creationTime,
      Map<String, Integer> additionalParams);
}

