package oogasalad.model.gameobject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import oogasalad.model.gameObjectFactories.GameObjectFactory;
import oogasalad.model.api.ReadOnlyGameTime;

/**
 * Represents a tile within the game world that can contain various game objects including
 * collectables, structures, and land. Each tile is capable of interacting with items and can change
 * its contents based on these interactions or over time.
 */

public class Tile implements Updatable, Interactable {

  // TODO: Will eventually need to externalize configuration
  public static final String defaultCollectableID = "COLLECTABLE";
  private Collectable collectable;
  private Structure structure;
  private Land land;
  private final GameObjectFactory factory;
  private ReadOnlyGameTime lastUpdatingGameTime;


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
   */
  @Override
  public void interact(Item item) {
    boolean interactionHandled =
        handleInteractionIfValid(collectable, item, () -> handleCollectableInteraction(item), false)
            || handleInteractionIfValid(structure, item, () -> handleStructureInteraction(item), false)
            || handleInteractionIfValid(land, item, () -> handleLandInteraction(item),
            land.getIfItemCanBePlacedHere(item));
  }

  @Override
  public boolean interactionValid(Item item) {
    return collectable.interactionValid(item) || structure.interactionValid(item) ||
        land.interactionValid(item);
  }

  /**
   * Executes interaction logic if the specified game object is valid for interaction with the given
   * item.
   *
   * @param gameObject         The game object to check and interact with.
   * @param item               The item used for the interaction.
   * @param interactionHandler The logic to execute for the interaction.
   * @param additionalCheck    An additional boolean check for if an interaction is valid
   *                           that is specific to each GameObject
   * @return True if the interaction was valid and handled, false otherwise.
   */
  private boolean handleInteractionIfValid(GameObject gameObject, Item item,
      Runnable interactionHandler, boolean additionalCheck) {
    if (gameObject != null && (gameObject.interactionValid(item) || additionalCheck)) {
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
    if (collectable == null && structure.isHarvestable() && structure.interactionValid(item)) {
      collectable =
          (Collectable) factory.createNewGameObject(defaultCollectableID, lastUpdatingGameTime,
             structure.getItemsOnDestruction());
    }
    else {
      structure.interact(item);
    }
  }

  /**
   * Handles the specific interaction logic for a land object within the tile.
   *
   * @param item The item interacting with the land.
   */
  private void handleLandInteraction(Item item) {
    if (land.getIfItemCanBePlacedHere(item) && structure == null) {
      structure = (Structure) factory.createNewGameObject(
          land.getStructureBasedOnItem(item), lastUpdatingGameTime,
          new HashMap<>());
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
   */
  @Override
  public void update(ReadOnlyGameTime gameTime) {
    lastUpdatingGameTime = gameTime;
    updateGameObject(() -> collectable, this::setCollectable, gameTime);
    updateGameObject(() -> structure, this::setStructure, gameTime);
    updateGameObject(() -> land, this::setLand, gameTime);
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
  private <T extends GameObject> void updateGameObject(Supplier<T> getter, Consumer<T> setter,
      ReadOnlyGameTime gameTime) {
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
   * @return A Map with every item id and their quantity to be added to the game as a result of a collectable
   * being collected.
   */
  public Map<String, Integer> itemReturns() {
    if (collectable != null && collectable.shouldICollect()) {
      Map<String, Integer> items = collectable.getItemsOnCollection();
      collectable = null;
      return items;
    }
    return null;
  }

  /**
   * Retrieves the paths to the images representing the current state of the tile's contents, which
   * can include collectables, structures, and land. This is useful for graphical representation of
   * the tile in the game's user interface.
   *
   * @return A list containing the image paths for the collectable, structure, and land on this tile,
   *         if available. The list may be empty if none of the components have an associated image.
   */
  public List<String> getImages() {
    List<GameObject> gameObjects = Arrays.asList(land, structure, collectable);
    return gameObjects.stream()
        .filter(obj -> obj != null && obj.getImagePath() != null)
        .map(GameObject::getImagePath)
        .collect(Collectors.toList());
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

  /**
   * Retrieves the ID of the current collectable object on this tile.
   * This method returns the ID of the collectable if one is present; otherwise, it returns null.
   * Collectables are game objects that players can interact with to collect items or trigger events.
   *
   * @return The ID of the current Collectable on the tile, or null if no collectable is present.
   */
  public String getCollectableId() {
    return collectable != null ? collectable.getId() : null;
  }

  /**
   * Retrieves the ID of the current structure object on this tile.
   * This method returns the ID of the structure if one is present; otherwise, it returns null.
   * Structures are static game objects that often interact with items or affect game mechanics on their tile.
   *
   * @return The ID of the current Structure on the tile, or null if no structure is present.
   */
  public String getStructureId() {
    return structure != null ? structure.getId() : null;
  }

  /**
   * Retrieves the ID of the current land object on this tile.
   * This method returns the ID of the land if one is present; otherwise, it returns null.
   * Land objects define the basic properties of the tile such as what can be placed or grown on it.
   *
   * @return The ID of the current Land on the tile, or null if no land is present.
   */
  public String getLandId() {
    return land != null ? land.getId() : null;
  }
}

