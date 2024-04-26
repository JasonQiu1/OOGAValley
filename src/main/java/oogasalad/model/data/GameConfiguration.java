package oogasalad.model.data;

import java.io.IOException;
import java.nio.file.Paths;
import oogasalad.model.api.ReadOnlyGameConfigurablesStore;
import oogasalad.model.api.ReadOnlyGameConfiguration;
import oogasalad.model.api.ReadOnlyGameState;
import oogasalad.model.api.ReadOnlyProperties;
import oogasalad.model.api.exception.BadGsonLoadException;
import oogasalad.model.api.exception.InvalidRuleType;
import oogasalad.model.gameplay.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A class that represents the set of all configurations for a game in the framework.
 * <p>
 * It includes the configured game rules and default initial state of the game.
 *
 * @author Jason Qiu
 */
public class GameConfiguration implements ReadOnlyGameConfiguration {

  // TODO: Externalize this to a configuration file.
  // The path to the game configurations directory from the data directory.
  public static final String GAMECONFIGURATION_DIRECTORY_PATH = "gameconfigurations";

  /**
   * Initializes the game configuration to a set of default rules and initial state.
   */
  public GameConfiguration() {
    try {
      rules = Properties.of(Paths.get("templates", "GameRulesGrouped").toString());
    } catch (IOException e) {
      LOG.error("Couldn't load default GameRules 'templates/GameRulesGrouped.json'.");
      throw new RuntimeException(e);
    }
    DataValidation.validateProperties(rules);
    try {
      configurablesStore = CONFIGURABLES_DATA_FACTORY.load(
          Paths.get("templates", "ConfigurablesStore").toString());
    } catch (IOException e) {
      LOG.error("Couldn't load default ConfigurablesStore 'templates/ConfigurablesStore.json'.");
      throw new RuntimeException(e);
    }
    initialState = new GameState();
  }

  /**
   * Creates and returns an instance of {@link GameConfiguration} from a JSON file. Also loads the
   * configurables store with the same name.
   *
   * @param dataFilePath the path to the JSON file.
   * @return the created instance of {@link GameConfiguration}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link GameConfiguration}
   * @throws IOException          if the filePath could not be opened.
   */
  public static GameConfiguration of(String dataFilePath) throws BadGsonLoadException, IOException {
    GameConfiguration load = GAME_CONFIGURATION_DATA_FACTORY.load(
        Paths.get(GAMECONFIGURATION_DIRECTORY_PATH, dataFilePath).toString());
    configurablesStore = GameConfigurablesStore.of(dataFilePath);
    return load;
  }

  /**
   * Returns the read-only ConfigurablesStore for the game configuration.
   *
   * @return the read-only ConfigurablesStore for the game configuration.
   */
  public static ReadOnlyGameConfigurablesStore getConfigurablesStore() {
    return configurablesStore;
  }

  /**
   * Serializes the instance to a JSON file.
   * <p>
   * Also saves the configurables store of the same name.
   *
   * @param dataFilePath the path to the JSON file with the data directory as the root.
   * @throws IOException if there is an issue writing to the given dataFilePath.
   */
  @Override
  public void save(String dataFilePath) throws IOException {
    configurablesStore.save(dataFilePath);
    GAME_CONFIGURATION_DATA_FACTORY.save(
        Paths.get(GAMECONFIGURATION_DIRECTORY_PATH, dataFilePath).toString(), this);
  }

  @Override
  public ReadOnlyProperties getRules() {
    return rules;
  }

  @Override
  public ReadOnlyGameState getInitialState() {
    return initialState;
  }

  @Override
  public void updateRule(String rule, String newValue) throws InvalidRuleType {
    DataValidation.validate(rule, newValue);
    rules.update(rule, newValue);
  }


  public void getEditableInitialState(GameState initialState) {
    this.initialState = initialState;
  }


  /**
   * Returns the editable ConfigurablesStore for the game configuration.
   *
   * @return the editable ConfigurablesStore for the game configuration.
   */
  public static GameConfigurablesStore getEditableConfigurablesStore() {
    return configurablesStore;
  }

  private GameState initialState;
  private final Properties rules;
  private static GameConfigurablesStore configurablesStore;
  private static final DataFactory<GameConfiguration> GAME_CONFIGURATION_DATA_FACTORY =
      new DataFactory<>(GameConfiguration.class);
  private static final DataFactory<GameConfigurablesStore> CONFIGURABLES_DATA_FACTORY =
      new DataFactory<>(GameConfigurablesStore.class);
  private static final Logger LOG = LogManager.getLogger(GameConfiguration.class);
}
