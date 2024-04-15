package oogasalad.model.gameobject;

/**
 * Represents entities within the game that are capable of supporting planting operations.
 * Implementing this interface indicates that the game object can be interacted with in a manner
 * that involves planting, such as seeds or other growable entities. This could apply to various
 * types of terrain, objects, or custom game elements designed to support planting behavior.
 */
public interface Plantable {

  /**
   * Determines if the entity is currently in a state that allows planting operations.
   * This method should be used to check if a planting action can be performed on the entity
   * based on its current state, properties, or environmental conditions.
   *
   * @return {@code true} if the entity is plantable; {@code false} otherwise.
   */
  boolean getIsPlantable();

}