package oogasalad.Game.GameModel.exception;

/**
 * Thrown when a key is not found in a collection.
 *
 * @author Jason Qiu
 */
public class KeyNotFoundException extends RuntimeException {

  public KeyNotFoundException(String invalidKey) {
    this.invalidKey = invalidKey;
  }

  public KeyNotFoundException(String invalidKey, Exception exception) {
    super(KeyNotFoundException.class.getSimpleName(), exception);
    this.invalidKey = invalidKey;
  }

  public String getInvalidKey() {
    return invalidKey;
  }

  private final String invalidKey;
}
