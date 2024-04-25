package oogasalad.fake;

import oogasalad.fake.api.GameInterface;

public class Game implements GameInterface {


  @Override
  public GameConfig getGameConfig() {
    return null;
  }

  @Override
  public GameState getGameState() {
    return null;
  }
  
  @Override
  public void save(String fileName) {

  }
}
