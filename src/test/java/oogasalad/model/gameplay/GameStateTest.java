package oogasalad.model.gameplay;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.nio.file.Paths;
import oogasalad.model.data.DataFactory;
import oogasalad.model.data.GameConfiguration;
import org.junit.jupiter.api.Test;

class GameStateTest {

  private static final String TEST_DATA_DIRECTORY = "test";
  public DataFactory<GameState> gameStateFactory = new DataFactory<>(GameState.class);

  @Test
  void saveDefaultGameState() {
    GameConfiguration defaultConfig = new GameConfiguration();
    GameState defaultState = new GameState(defaultConfig.getRules());
    defaultState.getEditableBag().addItems(defaultConfig.getRules().getStringIntegerMap("startingItems"));
    assertDoesNotThrow(() -> save("defaultState", defaultState));
  }

  private void save(String dataFilePath, GameState state) throws IOException {
    gameStateFactory.save(Paths.get(TEST_DATA_DIRECTORY, dataFilePath).toString(), state);
  }
}