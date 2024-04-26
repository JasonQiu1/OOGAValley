package oogasalad.model.gameobject;

/**
 * @deprecated
 * A record that encapsulates the image identifiers for various types of game objects within a
 * single tile or unit of game space. This record is designed to streamline the process of
 * retrieving and displaying the images associated with the different elements that can exist within
 * the same location in the game world, such as collectables, structures, and land. By grouping
 * these image identifiers together, the {@code ImageRecord} facilitates efficient access and
 * management of visual representations, especially useful in rendering the game's graphical
 * interface.
 *
 * @param collectableImage The image identifier for a collectable item present in the game location.
 *                         This can be {@code null} if no collectable is present.
 * @param structureImage   The image identifier for a structure present in the game location. This
 *                         can be {@code null} if no structure is present.
 * @param landImage        The image identifier for the land or terrain present in the game
 *                         location. This can be {@code null} if the location does not involve
 *                         explicit land or terrain imagery.
 */
public record ImageRecord(String collectableImage, String structureImage, String landImage) {

}
