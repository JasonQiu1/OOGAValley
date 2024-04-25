package oogasalad.fake.api;

import oogasalad.fake.GameConfig;
import oogasalad.fake.GameState;

public interface GameInterface {

  GameConfig getGameConfig();

  GameState getGameState();

  void save(String fileName);
}
