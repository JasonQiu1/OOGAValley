package oogasalad.Game.GameModel.PropertiesOfGameObjects;

import oogasalad.Game.GameModel.GameTime;
import oogasalad.Game.GameModel.Item;
import oogasalad.Game.GameModel.ReadOnlyProperties;

public abstract class GameObjectProperties {

  private ReadOnlyProperties properties;

  protected GameObjectProperties(ReadOnlyProperties properties) {
    this.properties = properties;
  }

  protected void setProperties(ReadOnlyProperties properties) {
    this.properties = properties;
  }

  public long modifiedTimeToUpdate(GameTime gameTime) {
    return properties.getInteger("updateTime");
  }

  public boolean doesExpire() {
    return properties.getBoolean("expirable");
  }

  public String getNextUpdateGameObject() {
    return properties.getString("updateTransformation");
  }

  public long getTimeToExpired() {
    return properties.getInteger("expireTime");
  }

  public boolean validInteractingItem(Item item) {
    return properties.getStringMap("interactTransformations").containsKey(item.toString());
  }

  public String nextInteractingGameObject(Item item) {
    return properties.getStringMap("interactTransformations").get(item.toString());
  }

  public String getImagePath() {
    return properties.getString("image");
  }

  public String getGameObjectAfterExpiration() {
    return properties.getString("expireTransformation");
  }
}
