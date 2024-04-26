package oogasalad.fake.api;

import oogasalad.fake.GameState;
import oogasalad.fake.config.GameConfig;

public interface GameInterface {

  GameConfig getGameConfig();

  GameState getGameState();

  void save(String fileName);
}
