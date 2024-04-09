package oogasalad.Game.GameModel.PropertiesOfGameObjects;

import java.util.List;
import java.util.Map;
import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.WrapperStateAndItem;

public abstract class GameObjectProperties {

  private String nextUpdateGameObject;
  private Map<String, String> interactingNewGameObjectMapings;
  private Map<String, Double> conditionalPreferenceMultipliers;
  private String image;
  private String gameObjectAfterExpiration;
  private boolean expiringState;
  private int expiringTime;
  private int defaultUpdateTime;

  public long modifiedTimeToUpdate(GameTime gameTime) {
    return defaultUpdateTime;
  }

  public boolean doesExpire() {
    return expiringState;
  }

  public long getTimeToExpired() {
    return expiringTime;
  }

  public boolean validInteractingItem(Item item) {
    return interactingNewGameObjectMapings.containsKey(item.toString());
  }

  public String nextInteractingGameObject(Item item) {
    return interactingNewGameObjectMapings.get(item.toString()
    );
  }

  public String getImagePath() {
    return image;
  }

  public String getGameObjectAfterExpiration() {
    return gameObjectAfterExpiration;
  }
}
