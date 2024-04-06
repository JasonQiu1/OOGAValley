package oogasalad.Game.GameModel.GameObjects;

public interface Collect {

  int getQuantityOnCollection();

  String getItemIdOnCollection();

  boolean shouldICollect();

}
