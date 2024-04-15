package oogasalad.model.gameobject;

import oogasalad.model.GameTime;
import oogasalad.model.Item;

/**
 * Represents a tile within the game world that can contain various game objects including
 * collectables, structures, and land. Each tile is capable of interacting with items and can change
 * its contents based on these interactions or over time.
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
   * This method checks each game object (collectable, structure, land) in the tile for interaction
   * validity and processes the first valid interaction found. Collectable interactions can result
   * in items being added to the game as a result of the interaction.
   *
   * @param item The item to interact with the tile's contents.
   * @return ItemsToAdd, representing any items to be added to the game as a result of the
   * interaction. Returns null if no items are to be added.
   */
  public ItemsToAdd interact(Item item) {
    boolean interactionHandled =
        handleInteractionIfValid(collectable, item, () -> handleCollectableInteraction(item))
            || handleInteractionIfValid(structure, item, () -> handleStructureInteraction(item))
            || handleInteractionIfValid(land, item, () -> handleLandInteraction(item));

    return interactionHandled && collectable != null ? itemReturns() : null;
  }

  /**
   * Executes interaction logic if the specified game object is valid for interaction with the given
   * item.
   *
   * @param gameObject         The game object to check and interact with.
   * @param item               The item used for the interaction.
   * @param interactionHandler The logic to execute for the interaction.
   * @return True if the interaction was valid and handled, false otherwise.
   */
  private boolean handleInteractionIfValid(GameObject gameObject, Item item,
      Runnable interactionHandler) {
    if (gameObject != null && gameObject.interactionValid(item)) {
      interactionHandler.run();
      return true;
    }
    return false;
  }

  /**
   * Handles the specific interaction logic for a collectable object within the tile.
   *
   * @param item The item interacting with the collectable.
   */
  private void handleCollectableInteraction(Item item) {
    collectable.interact(item);
  }

  /**
   * Handles the specific interaction logic for a structure object within the tile.
   *
   * @param item The item interacting with the structure.
   */
  private void handleStructureInteraction(Item item) {
    if (collectable == null && structure.isHarvestable()) {
      collectable =
          (Collectable) factory.createNewGameObject(structure.getCollectableOnDestruction());
    }
    structure.interact(item);
  }

  /**
   * Handles the specific interaction logic for a land object within the tile.
   *
   * @param item The item interacting with the land.
   */
  private void handleLandInteraction(Item item) {
    if (land.getIsPlantable() && item.getIsSeed() && structure == null) {
      structure = (Structure) factory.createNewGameObject(item.toString());
    }
    land.interact(item);
  }

  /**
   * Updates the state of the tile and its contents based on the current game time. This method is
   * intended to be called periodically, allowing the game objects within the tile to update based
   * on time progression and potentially change their state or interactions.
   *
   * @param gameTime The current game time.
   * @return ItemsToAdd, which represents any items to be added to the game as a result of the
   * updates.
   */
  public ItemsToAdd update(GameTime gameTime) {
    executeIfNotNull(() -> collectable.update(gameTime), collectable, gameTime);
    executeIfNotNull(() -> structure.update(gameTime), structure, gameTime);
    executeIfNotNull(() -> land.update(gameTime), land, gameTime);
    return itemReturns();
  }

  /**
   * Executes the given update logic for a game object if it is not null.
   *
   * @param updateLogic The update logic to be executed.
   * @param gameObject  The game object to check for nullity.
   * @param gameTime The current game time.
   */
  private void executeIfNotNull(Runnable updateLogic, GameObject gameObject, GameTime gameTime) {
    if (gameObject != null) {
      updateLogic.run();
      gameObject.checkAndUpdateExpired(gameTime);
    }
  }

  /**
   * Determines if any items should be added to the game based on the interactions and updates that
   * occurred within the tile, particularly with collectables.
   *
   * @return ItemsToAdd representing the items to be added as a result, or null if no items are to
   * be added.
   */
  private ItemsToAdd itemReturns() {
    if (collectable != null && collectable.shouldICollect()) {
      return new ItemsToAdd(collectable.getQuantityOnCollection(),
          collectable.getItemIdOnCollection());
    }
    return null;
  }

  /**
   * Retrieves the paths to the images representing the current state of the tile's contents, which
   * can include collectables, structures, and land. This is useful for graphical representation of
   * the tile in the game's user interface.
   *
   * @return An ImageRecord containing the image paths for the collectable, structure, and land on
   * this tile.
   */
  public ImageRecord getImages() {
    return new ImageRecord(collectable.getImagePath(), structure.getImagePath(),
        land.getImagePath());
  }

  /**
   * Sets the collectable object for this tile.
   * This method assigns a Collectable instance to the tile, potentially replacing an existing collectable.
   *
   * @param collectable The Collectable object to be set on this tile.
   */
  public void setCollectable(Collectable collectable) {
    this.collectable = collectable;
  }

  /**
   * Sets the structure object for this tile.
   * This method assigns a Structure instance to the tile, potentially replacing an existing structure.
   *
   * @param structure The Structure object to be set on this tile.
   */
  public void setStructure(Structure structure) {
    this.structure = structure;
  }

  /**
   * Sets the land object for this tile.
   * This method assigns a Land instance to the tile, potentially replacing an existing land type.
   *
   * @param land The Land object to be set on this tile.
   */
  public void setLand(Land land) {
    this.land = land;
  }
}

