package oogasalad.Game.GameModel;

/**
 * A class that represents the set of all configurations for a game in the framework.
 * <p>
 * It includes the configured game rules and default initial state of the game.
 *
 * @author Jason Qiu
 */
public class GameConfiguration extends Properties {

  /**
   * Initializes the game configuration to a set of default rules and initial state.
   */
  public GameConfiguration() {
    // TODO: INITIALIZE RULES WITH DEFAULT PROPERTIES
    // e.g. super("pathToDefaultRules");
    super();
    initialState = new GameState();
  }

  public GameState getInitialState() {
    return initialState;
  }

  public void setInitialState(GameState initialState) {
    this.initialState = initialState;
  }

  private GameState initialState;
}
