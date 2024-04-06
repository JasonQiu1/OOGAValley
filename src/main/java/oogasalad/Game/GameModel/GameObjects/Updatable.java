package oogasalad.Game.GameModel.GameObjects;

import oogasalad.Game.GameModel.GameTime;

public interface Updatable {

  void update(GameTime gameTime);

  int getState();

  void setState(int newState);

}
