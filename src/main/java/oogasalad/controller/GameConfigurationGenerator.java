
package oogasalad.controller;

import java.io.IOException;
import oogasalad.model.data.GameConfiguration;

/**
 * The GameConfigurationGenerator class is responsible for generating default GameConfiguration objects.
 */
public class GameConfigurationGenerator {

  /**
   * Constructs a new GameConfigurationGenerator.
   */
  public GameConfigurationGenerator() {

  }

  /**
   * Creates a default GameConfiguration object.
   *
   * @return a new GameConfiguration object with default settings
   */
  public GameConfiguration createDefault() {
    return new GameConfiguration();
  }

  public GameConfiguration returnConfig(String filePath) {
    try {
      GameConfiguration gameConfiguration =  GameConfiguration.of(filePath);
      return gameConfiguration;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
