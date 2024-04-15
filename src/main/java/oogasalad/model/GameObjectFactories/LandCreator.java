package oogasalad.model.GameObjectFactories;

import java.util.Map;
import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.Land;
import oogasalad.model.gameplay.GameTime;

/**
 * A creator class for constructing {@link Land} game objects.
 * Implements the {@link GameObjectCreator} interface to provide a method
 * for instantiating {@link Land} objects based on provided properties.
 */
public class LandCreator implements GameObjectCreator {

  /**
   * Creates a {@link Land} object with the specified properties and game time.
   * This method initializes a Land, setting up its state based on the properties provided.
   *
   * @param properties The properties defining characteristics and behavior of the Land.
   *                    These properties should specify necessary attributes of the land.
   * @param creationTime The game time at which the Land is being created, which can influence
   *                     its initial conditions or environmental settings.
   * @param additionalParams A map containing additional parameters required for creating the Land.
   *                         This implementation of Land does not utilize additionalParams, but the parameter
   *                         is included to maintain interface consistency.
   * @return A new {@link Land} object, initialized and ready for game logic integration.
   */
  @Override
  public GameObject create(ReadOnlyProperties properties, GameTime creationTime,
      Map<String, Integer> additionalParams) {
    return new Land(properties, creationTime);
  }
}
