package oogasalad.Game.GameModel;

public interface Updatable {

  void update(GameTime gameTime);

  int getState();

  void setState(int newState);

}
