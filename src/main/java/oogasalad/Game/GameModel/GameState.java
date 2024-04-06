package oogasalad.Game.GameModel;

import java.nio.file.Paths;
import oogasalad.Game.GameModel.exception.BadGsonLoadException;

public class GameState {

  // TODO: Externalize this to a configuration file.
  // The path to the gamesaves directory from the data directory.
  public static String GAMESTATE_DIRECTORY_PATH = "gamesaves";

  /**
   * Creates and returns an instance of {@link GameState} from a JSON file.
   *
   * @param dataFilePath the path to the JSON file.
   * @return the created instance of {@link GameState}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link GameState}
   */
  public static GameState of(String dataFilePath) throws BadGsonLoadException {
    return new DataFactory<GameState>().load(
        Paths.get(GAMESTATE_DIRECTORY_PATH, dataFilePath).toString(), GameState.class);
  }
}