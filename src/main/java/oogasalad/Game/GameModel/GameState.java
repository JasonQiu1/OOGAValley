package oogasalad.Game.GameModel;

import java.io.IOException;
import java.nio.file.Paths;
import oogasalad.Game.GameModel.exception.BadGsonLoadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    return FACTORY.load(Paths.get(GAMESTATE_DIRECTORY_PATH, dataFilePath).toString(),
        GameState.class);
  }

  /**
   * Serializes the instance to a JSON file.
   *
   * @param dataFilePath the path to the JSON file with the data directory as the root.
   * @throws IOException if there is an issue writing to the given dataFilePath.
   */
  public void save(String dataFilePath) throws IOException {
    FACTORY.save(Paths.get(GAMESTATE_DIRECTORY_PATH, dataFilePath).toString(), this);
  }

  private static final DataFactory<GameState> FACTORY = new DataFactory<>();
  private static final Logger LOG = LogManager.getLogger(GameState.class);
}