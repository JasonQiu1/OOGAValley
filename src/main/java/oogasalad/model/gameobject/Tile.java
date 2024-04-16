package oogasalad.model.gameobject;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import oogasalad.model.GameObjectFactories.GameObjectFactory;
import oogasalad.model.data.Properties;
import oogasalad.model.gameplay.GameTime;

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
   * @param gameTime The current GameTime of the game.
   * @return ItemsToAdd, representing any items to be added to the game as a result of the
   * interaction. Returns null if no items are to be added.
   */
  public ItemsToAdd interact(Item item, GameTime gameTime) {
    boolean interactionHandled =
        handleInteractionIfValid(collectable, item, () -> handleCollectableInteraction(item))
            || handleInteractionIfValid(structure, item, () -> handleStructureInteraction(item, gameTime))
            || handleInteractionIfValid(land, item, () -> handleLandInteraction(item, gameTime));

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
   * @param gameTime The current GameTime of the game.
   */
  private void handleStructureInteraction(Item item, GameTime gameTime) {
    if (collectable == null && structure.isHarvestable()) {
      collectable =
          (Collectable) factory.createNewGameObject(null, gameTime,
             structure.getItemsOnDestruction());
      // TODO: Don't pass in null properties get that from somewhere
    }
    else {
      structure.interact(item);
    }
  }

  /**
   * Handles the specific interaction logic for a land object within the tile.
   *
   * @param item The item interacting with the land.
   * @param gameTime The current GameTime of the game.
   */
  private void handleLandInteraction(Item item, GameTime gameTime) {
    if (land.getIfItemCanBePlacedHere(item) && structure == null) {
      structure = (Structure) factory.createNewGameObject(null, gameTime,
          new HashMap<>());
      // TODO: Don't pass in null properties get that from somewhere
    }
    else {
      land.interact(item);
    }
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
    updateGameObject(() -> collectable, this::setCollectable, gameTime);
    updateGameObject(() -> structure, this::setStructure, gameTime);
    updateGameObject(() -> land, this::setLand, gameTime);
    return itemReturns();
  }

  /**
   * Updates a specific game object within the tile if it is not null, checks for its expiration,
   * and potentially sets it to null if it has expired. This method uses generic types to allow
   * flexibility with different types of game objects.
   *
   * @param <T> The type of the game object, extending {@link GameObject}.
   * @param getter A {@link Supplier} that returns the current game object of type T.
   * @param setter A {@link Consumer} that accepts a game object of type T to set the object,
   *               typically used to set the object to null if expired.
   * @param gameTime The current game time, used to determine if the game object should update
   *                 or expire based on the game logic.
   */
  private <T extends GameObject> void updateGameObject(Supplier<T> getter, Consumer<T> setter, GameTime gameTime) {
    T gameObject = getter.get();
    if (gameObject != null) {
      gameObject.update(gameTime);
      if (gameObject.checkAndUpdateExpired(gameTime)) {
        setter.accept(null);
      }
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
   * Sets the collectable object for this tile. This method assigns a Collectable instance to the
   * tile, potentially replacing an existing collectable.
   *
   * @param collectable The Collectable object to be set on this tile.
   */
  public void setCollectable(Collectable collectable) {
    this.collectable = collectable;
  }

  /**
   * Sets the structure object for this tile. This method assigns a Structure instance to the tile,
   * potentially replacing an existing structure.
   *
   * @param structure The Structure object to be set on this tile.
   */
  public void setStructure(Structure structure) {
    this.structure = structure;
  }

  /**
   * Sets the land object for this tile. This method assigns a Land instance to the tile,
   * potentially replacing an existing land type.
   *
   * @param land The Land object to be set on this tile.
   */
  public void setLand(Land land) {
    this.land = land;
  }
}

