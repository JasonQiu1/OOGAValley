package oogasalad.model.TileTesting;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import oogasalad.model.gameobject.Tile;
import oogasalad.model.gameobject.Collectable;
import oogasalad.model.gameobject.Structure;
import oogasalad.model.gameobject.Land;
import oogasalad.model.gameobject.Item;
import oogasalad.model.gameplay.GameTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

  private Tile tile;
  private Collectable collectable;
  private Structure structure;
  private Land land;
  private Item item;
  private GameTime gameTime;

  @BeforeEach
  public void setUp() {
    tile = new Tile(); // Assumes the Tile class constructor initializes its contents
    collectable = mock(Collectable.class);
    structure = mock(Structure.class);
    land = mock(Land.class);
    item = mock(Item.class);
    gameTime = new GameTime(1, 1, 1);

    // Setup basic interactions for mock objects
    when(collectable.interactionValid(item)).thenReturn(true);
    when(structure.interactionValid(item)).thenReturn(false);
    when(land.interactionValid(item)).thenReturn(false);

    // Setup the tile's fields to use the mocks
    tile.setCollectable(collectable);
    tile.setStructure(structure);
    tile.setLand(land);
  }

  @Test
  public void testTileInteractWithCollectable() {
    tile.interact(item);
    verify(collectable, times(1)).interact(item);
    verify(structure, never()).interact(item);
    verify(land, never()).interact(item);
  }

  @Test
  public void testTileInteractionValidity() {
    assertTrue(tile.interactionValid(item), "Tile should return true for valid interaction with collectable");
  }

  @Test
  public void testTileUpdateCallsUpdateOnContents() {
    tile.update(gameTime);
    verify(collectable, times(1)).update(gameTime);
    verify(structure, times(1)).update(gameTime);
    verify(land, times(1)).update(gameTime);
  }

  @Test
  public void testCollectableInteractionResultsInItemCollection() {
    when(collectable.shouldICollect()).thenReturn(true);
    Map<String, Integer> expectedItems = Map.of("gem", 1);
    when(collectable.getItemsOnCollection()).thenReturn(expectedItems);

    Map<String, Integer> items = tile.itemReturns();
    assertEquals(expectedItems, items, "Items returned from tile should match expected collection");
  }

  @Test
  public void testTileImagePathsReflectsContents() {
    when(collectable.getImagePath()).thenReturn("path/to/collectable.png");
    when(structure.getImagePath()).thenReturn(null);
    when(land.getImagePath()).thenReturn("path/to/land.png");

    List<String> expectedPaths = List.of("path/to/collectable.png", "path/to/land.png");
    List<String> imagePaths = tile.getImages();

    assertEquals(expectedPaths.size(), imagePaths.size(), "Number of image paths should match");
    assertTrue(imagePaths.containsAll(expectedPaths), "Image paths should include paths from both collectable and land");
  }

  @Test
  public void testInteractionWithInvalidItem() {
    Item invalidItem = mock(Item.class);
    when(invalidItem.getName()).thenReturn("invalidItem");
    tile.interact(invalidItem);
    verify(collectable, never()).interact(any());
    verify(structure, never()).interact(any());
    verify(land, never()).interact(any());
  }

  @Test
  public void testInteractionOrderPrecedence() {
    when(collectable.interactionValid(item)).thenReturn(false);
    when(structure.interactionValid(item)).thenReturn(true);
    tile.interact(item);
    verify(structure).interact(item);
    verify(collectable, never()).interact(item);
    verify(land, never()).interact(item);
  }

  @Test
  public void testUpdateWithNoGameTimeChange() {
    GameTime sameTime = new GameTime(1, 1, 1);
    tile.update(sameTime);
    verify(collectable, never()).update(sameTime);
    verify(structure, never()).update(sameTime);
    verify(land, never()).update(sameTime);
  }

  @Test
  public void testStructureBecomesCollectableUponInteraction() {
    when(structure.isHarvestable()).thenReturn(true);
    when(structure.getItemsOnDestruction()).thenReturn(Collections.singletonMap("gem", 1));
    when(item.getName()).thenReturn("validTool");
    when(structure.interactionValid(item)).thenReturn(true);
    tile.interact(item);
    assertNotNull(tile.getCollectable());
    assertTrue(tile.getCollectable().getItemsOnCollection().containsKey("gem"));
  }

}

