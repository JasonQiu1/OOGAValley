package oogasalad.Game.GameModel;

public abstract class GameObject implements Interactable, Expirable, Updatable {

  private boolean expired;
  int state;
  private String id;
  private GameObjectProperties properties;


  public GameObject(String id, int startState, GameObjectProperties properties) {
    this.id = id;
    state = startState;
    this.properties = properties;
    expired = false;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean isExpired() {
    return expired;
  }

  @Override
  public int getState() {
    return state;
  }

}
