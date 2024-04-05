package oogasalad.Game.GameModel.PropertiesOfGameObjects;

import java.util.Map;
import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.WrapperStateAndItem;

public abstract class GameObjectProperties {

  private Map<Integer, Integer> updatingStateMapings;
  private Map<Integer, String> updatingNewGameObjectMapings;
  private Map<WrapperStateAndItem, Integer> interactingStateMapings;
  private Map<WrapperStateAndItem, String> interactingNewGameObjectMapings;
  private Map<String, Double> conditionalPreferenceMultipliers;
  private int expiringState;
  private long expiringTime;
  private long defaultUpdateTime;

  public long modifiedTimeToUpdate(GameTime gameTime) {
    return 0;
  }

  public int nextUpdatingState(int state) {
    return updatingStateMapings.get(state);
  }

  public int getExpiringState() {
    return expiringState;
  }

  public boolean nextStateIsNewGameObject(int state) {
    return updatingNewGameObjectMapings.containsKey(state);
  }

  public String nextUpdatingGameObject(int state) {
    return updatingNewGameObjectMapings.get(state);
  }

  public long getTimeToExpired() {
    return expiringTime;
  }

  public boolean validInteractingItem(int state, Item item) {
    return interactingStateMapings.containsKey(new WrapperStateAndItem(item, state));
  }

  public boolean nextInteractingStateIsNewGameObject(int state, Item item) {
    return interactingNewGameObjectMapings.containsKey(new WrapperStateAndItem(item, state));
  }

  public String nextInteractingGameObject(int state, Item item) {
    return interactingNewGameObjectMapings.get(new WrapperStateAndItem(item, state));
  }

  public int nextInteractingState(int state, Item item) {
    return interactingStateMapings.get(new WrapperStateAndItem(item, state));
  }
}
