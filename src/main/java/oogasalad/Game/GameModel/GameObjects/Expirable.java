package oogasalad.Game.GameModel.GameObjects;

public interface Expirable {

  boolean isExpired();

  String getGameObjectAfterExpiration();

}
