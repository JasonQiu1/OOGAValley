package oogasalad.database.realtime;

import java.util.HashMap;
import java.util.Map;

public class GameData {

  private Map<String, Player> players;
  private Map<String, Object> gameConfig;
  private Map<String, Object> currentState;

  public GameData() {

  }

  public GameData(String playerName, int initialScore) {
    this.players = new HashMap<>();
    this.players.put(playerName, new Player(playerName, initialScore));
    this.gameConfig = new HashMap<>();
    this.currentState = new HashMap<>();
  }

  public Map<String, Player> getPlayers() {
    return players;
  }
}
