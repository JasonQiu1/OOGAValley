package oogasalad.Game.GameModel;

import java.util.prefs.Preferences;

public abstract class GameObject implements ObjectsOfGame {

  private String id;
  private boolean expired;
  private Preferences preferences;
  int state;
  CoordinateOfGameObjectRecord coordinates;


  public GameObject(CoordinateOfGameObjectRecord coordinates) {
    this.coordinates = coordinates;

  }

  @Override
  public boolean isExpired() {
    return expired;
  }

  @Override
  public void interact(Item i1) {
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public CoordinateOfGameObjectRecord getCoordinates() {
    return coordinates;
  }

  @Override
  public void update(GameTime gameTime) {

  }
}
