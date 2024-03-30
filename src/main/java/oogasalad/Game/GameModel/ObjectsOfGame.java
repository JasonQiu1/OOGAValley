package oogasalad.Game.GameModel;

public interface ObjectsOfGame extends Expirable, Interactable, Updatable {

  String getId();

  CoordinateOfGameObjectRecord getCoordinates();

}
