package oogasalad.Game.GameModel;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import oogasalad.Game.GameModel.exception.BadGsonLoadException;
import oogasalad.Game.GameModel.exception.KeyNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A class that represents the set of all configurations for a game in the framework.
 * <p>
 * It includes the configured game rules and default initial state of the game.
 *
 * @author Jason Qiu
 */
public class GameConfiguration {

  // TODO: Externalize this to a configuration file.
  // The path to the game configurations directory from the data directory.
  public static final String GAMECONFIGURATION_DIRECTORY_PATH = "gameconfigurations";

  /**
   * Initializes the game configuration to a set of default rules and initial state.
   */
  public GameConfiguration() {
    try {
      rules = Properties.of(Paths.get("templates", "GameRules").toString());
    } catch (IOException e) {
      LOG.error("Couldn't load default GameRules 'templates/GameRules.json'.");
      throw new RuntimeException(e);
    }
    gameConfigurables = new HashMap<>();
    initialState = new GameState();
  }

  /**
   * Creates and returns an instance of {@link GameConfiguration} from a JSON file.
   *
   * @param dataFilePath the path to the JSON file.
   * @return the created instance of {@link GameConfiguration}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link GameConfiguration}
   * @throws IOException          if the filePath could not be opened.
   */
  public static GameConfiguration of(String dataFilePath) throws BadGsonLoadException, IOException {
    return FACTORY.load(Paths.get(GAMECONFIGURATION_DIRECTORY_PATH, dataFilePath).toString());
  }

  /**
   * Serializes the instance to a JSON file.
   *
   * @param dataFilePath the path to the JSON file with the data directory as the root.
   * @throws IOException if there is an issue writing to the given dataFilePath.
   */
  public void save(String dataFilePath) throws IOException {
    FACTORY.save(Paths.get(GAMECONFIGURATION_DIRECTORY_PATH, dataFilePath).toString(), this);
  }

  public ReadOnlyProperties getRules() {
    return rules;
  }

  /**
   * Gets the read-only version of a configurable (GameObject, Item, etc.) that exists in this game
   * configuration.
   *
   * @param id the id of the configurable.
   * @return the ReadOnlyProperties of the configurable.
   * @throws KeyNotFoundException if the id does not exist.
   */
  public ReadOnlyProperties getConfigurable(String id) throws KeyNotFoundException {
    if (!gameConfigurables.containsKey(id)) {
      LOG.error("Could not find properties for id '{}'.", id);
      throw new KeyNotFoundException(id);
    }
    return gameConfigurables.get(id);
  }

  public GameState getInitialState() {
    return initialState;
  }

  public void setInitialState(GameState initialState) {
    this.initialState = initialState;
  }

  private GameState initialState;
  private final Properties rules;
  private final Map<String, Properties> gameConfigurables;
  private static final DataFactory<GameConfiguration> FACTORY =
      new DataFactory<>(GameConfiguration.class);
  private static final Logger LOG = LogManager.getLogger(GameConfiguration.class);
}
