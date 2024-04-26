package oogasalad.model.api;

import java.util.List;

import oogasalad.model.api.exception.UnableToSetGameObject;
import oogasalad.model.gameobject.GameObject;
import oogasalad.model.gameobject.ItemsToAdd;

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
   * Set the number of rows
   *
   * @param height new number of rows
   */
  void setHeight(int height);

  /**
   * Set the number of columns
   *
   * @param width new number of columns
   */
  void setWidth(int width);

  /**
   * Sets a GameObject at the specified coordinates within the game world.
   *
   * @param gameObject The game object to set.
   * @param x          The x-coordinate of the tile.
   * @param y          The y-coordinate of the tile.
   * @param z          The z-coordinate of the tile.
   * @throws UnableToSetGameObject If there is an error setting the game object.
   */
  void setTileGameObject(GameObject gameObject, int x, int y, int z);

    void setTileGameObject(String id, int x, int y, int z);

    /**
   * Shifts every tile to the right and adds a column on the left
   */
  void shiftRightAndAddColumn();

  /**
   * Shifts every tile to the left and removes a column on the left
   */
  void shiftLeftAndRemoveColumn();

  /**
   * Shifts every tile to the up and removes a row on the top
   */
  void shiftUpAndRemoveRow();

  /**
   * Shifts every tile to the down and adds a row on the top
   */
  void shiftDownAndAddRow();

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

  /**
   * Returns a string list of the names of every gameObject at a location column, row, depth
   * //TODO: Add to api doc
   * @return string list of the names of every gameObject at a location
   */
  List<String> getTileContents(int column, int row, int depth);

  /**
   * Removes the top gameObject in the selected tile at a column, row, depth
   * Order of removal: Collectable, Structure, Land
   * //TODO: Add to api doc
   */
  void removeTileTop(int column, int row, int depth);
}

