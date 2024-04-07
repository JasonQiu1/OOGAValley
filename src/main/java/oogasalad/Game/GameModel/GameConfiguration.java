package oogasalad.Game.GameModel;

import java.io.IOException;
import java.nio.file.Paths;
import oogasalad.Game.GameModel.exception.BadGsonLoadException;
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
  private static final DataFactory<GameConfiguration> FACTORY =
      new DataFactory<>(GameConfiguration.class);
  private static final Logger LOG = LogManager.getLogger(GameConfiguration.class);
  private Properties rules;
  private GameState initialState;

  /**
   * Initializes the game configuration to a set of default rules and initial state.
   */
  public GameConfiguration() {
    // TODO: MAKE DEFAULT RULES FILE TO LOAD BY DEFAULT, THEN UNCOMMENT BELOW
    // rules = Properties.of("pathToDefaultRules");
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

  public Properties getRules() {
    return rules;
  }

  public void setRules(Properties rules) {
    // TODO: VALIDATE RULES BEFORE SETTING THEM
    this.rules = rules;
  }

  public GameState getInitialState() {
    return initialState;
  }

  public void setInitialState(GameState initialState) {
    this.initialState = initialState;
  }
}
