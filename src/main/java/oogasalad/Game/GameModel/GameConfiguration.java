package oogasalad.Game.GameModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import oogasalad.Game.GameModel.exception.BadGsonLoadException;

/**
 * A class that represents the set of all configurations for a game in the framework.
 * <p>
 * It includes the configured game rules and default initial state of the game.
 *
 * @author Jason Qiu
 */
public class GameConfiguration {

  private Properties rules;
  private GameState initialState;

  /**
   * Initializes the game configuration to a set of default rules and initial state.
   */
  public GameConfiguration() {
    // TODO: INITIALIZE RULES WITH DEFAULT PROPERTIES AND VALIDATE THEM
    // e.g. rules = new Properties("pathToDefaultRules");
    rules = new Properties();
    initialState = new GameState();
  }

  /**
   * Creates and returns an instance of {@link GameConfiguration} from a JSON file.
   *
   * @param filePath the path to the JSON file.
   * @return the created instance of {@link GameConfiguration}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link GameConfiguration}
   */
  public static GameConfiguration of(String filePath) throws BadGsonLoadException {
    Gson gson = new Gson();
    try {
      return gson.fromJson(filePath, GameConfiguration.class);
    } catch (JsonSyntaxException e) {
      // TODO: LOG MESSAGES AND HANDLE ERROR
      throw new BadGsonLoadException(filePath, GameConfiguration.class.getSimpleName(), e);
    }
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
