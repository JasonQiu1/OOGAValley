package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.Item;

public interface Interactable {

  String interact(Item item);

  boolean interactionValid(Item item);
}
