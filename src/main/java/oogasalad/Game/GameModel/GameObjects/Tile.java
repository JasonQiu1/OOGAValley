package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;

/**
 * Represents a tile within the game world that can contain various game objects including
 * collectables, structures, and land. Each tile is capable of interacting with items and can
 * change its contents based on these interactions or over time.
 */
public class Tile {

  private Collectable collectable;
  private Structure structure;
  private Land land;
  private final GameObjectFactory factory;

  /**
   * Constructs a new Tile with an associated GameObjectFactory for creating new game objects.
   */
  public Tile() {
    factory = new GameObjectFactory();
  }

  /**
   * Interacts with the provided item, potentially changing the state of the tile or its contents.
   * This method can result in the creation, transformation, or removal of collectables or structures
   * within the tile based on the item's properties and the current state of the tile's contents.
   *
   * @param item The item to interact with the tile's contents.
   * @return ItemsToAdd, which represents any items to be added to the game as a result of the interaction.
   */
  public ItemsToAdd interact(Item item) {
    // need checks for if each thing is empty == null or perhaps there is an empty state
    ItemsToAdd items = null;
    if (collectable != null && collectable.interactionValid(item)) {
      collectable.interact(item);
      items = itemReturns();
    } else if (structure != null && structure.interactionValid(item)) {
      if (collectable == null && structure.isHarvestable()) {
        collectable = (Collectable) factory.createNewGameObject
            (structure.getCollectableOnDestruction());
      }
      structure.interact(item);
    } else if (land.getIsPlantable() && item.getIsSeed() && structure == null) {
      structure = (Structure) factory.createNewGameObject(item.toString());
    } else {
      land.interact(item);
    }

    return items;
  }

  /**
   * Updates the state of the tile and its contents based on the given game time. This includes
   * updating collectables, structures, and the land itself, potentially leading to changes in their
   * state or interactions based on time progression.
   *
   * @param gameTime The current game time, used to drive updates to the tile's contents.
   * @return ItemsToAdd, which represents any items to be added to the game as a result of the update.
   */
  public ItemsToAdd update(GameTime gameTime) {
    ItemsToAdd items = itemReturns();
    collectable.update(gameTime);
    structure.update(gameTime);
    land.update(gameTime);

    return items;
  }

  private ItemsToAdd itemReturns() {
    ItemsToAdd items = null;
    if (collectable != null && collectable.shouldICollect()) {
      items = new ItemsToAdd(collectable.getQuantityOnCollection(),
          collectable.getItemIdOnCollection());
    }
    return items;
  }

  /**
   * Retrieves the paths to the images representing the current state of the tile's contents.
   * This can be used for graphical representation of the tile in the game's user interface.
   *
   * @return An ImageRecord containing the image paths for the collectable, structure, and land on this tile.
   */
  public ImageRecord getImages() {
    return new ImageRecord(collectable.getImagePath(), structure.getImagePath(),
        land.getImagePath());
  }
}
