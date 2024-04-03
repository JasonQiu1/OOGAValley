package oogasalad.Game.GameModel.exception;

public class KeyNotFoundException extends RuntimeException {

  public KeyNotFoundException(String key) {
    invalidKey = key;
  }

  public KeyNotFoundException(String key, Exception exception) {
    super(KeyNotFoundException.class.getSimpleName(), exception);
    invalidKey = key;
  }

  public String getInvalidKey() {
    return invalidKey;
  }

  private final String invalidKey;
}
