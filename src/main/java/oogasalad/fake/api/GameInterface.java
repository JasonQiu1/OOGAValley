package oogasalad.fake.api;

import java.io.IOException;
import oogasalad.fake.GameState;
import oogasalad.fake.api.exception.SaveNotValidException;
import oogasalad.fake.config.GameConfig;
import oogasalad.fake.map.GameMap;

public interface GameInterface {

  GameConfig getGameConfig();

  GameState getGameState();

  GameMap getGameMap();

  void save(String folderName) throws IOException, SaveNotValidException;
}
