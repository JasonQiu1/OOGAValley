package oogasalad.Game.GameModel;

public class Tile extends GameObject {
  private TileProperties properties;

  @Override
  void interact(Item selectedItem) {
    if (properties.getCorrectSeeds().contains(selectedItem) && plantable) {
      setPlanted
    }

  }
}