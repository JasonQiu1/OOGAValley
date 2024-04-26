package oogasalad.model.api;

import java.util.List;

import oogasalad.model.api.exception.UnableToSetGameObject;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.ItemsToAdd;
import oogasalad.model.gameobject.Updatable;

/**
 * Defines read-only operations that can be performed on the game world within a game. This
 * interface allows querying and interacting with the game world without modifying its underlying
 * state directly.
 */
public interface ReadOnlyGameWorld {

  /**
   * Retrieves a list of image paths that represent the visual state of the game world at the
   * specified location. This can be used for rendering the game world on a UI.
   *
   * @param width  The width coordinate where the image should be fetched.
   * @param height The height coordinate where the image should be fetched.
   * @param depth  The depth coordinate where the image should be fetched.
   * @return A list of strings, each representing a path to an image resource.
   */
  List<String> getImagePath(int width, int height, int depth);

  /**
   * Get the number of rows
   *
   * @return number of rows
   */
  int getHeight();

  /**
   * Get the number of columns
   *
   * @return number of columns
   */
  int getWidth();

  /**
   * Get the depth of the model
   *
   * @return depth
   */
  int getDepth();
}

