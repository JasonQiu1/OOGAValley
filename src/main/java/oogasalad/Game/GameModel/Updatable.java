package oogasalad.Game.GameModel;

public interface Updatable {

  String update(GameTime gameTime);

  int getState();

  void setState(int newState);

}
