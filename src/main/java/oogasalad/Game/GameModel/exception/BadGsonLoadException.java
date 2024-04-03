package oogasalad.Game.GameModel.exception;

public class BadGsonLoadException extends RuntimeException {

  public BadGsonLoadException(String key) {
    super(BadGsonLoadException.class.getSimpleName());
    filePath = key;
  }

  public BadGsonLoadException(String key, Exception exception) {
    super(BadGsonLoadException.class.getSimpleName(), exception);
    filePath = key;
  }

  public String getFilePath() {
    return filePath;
  }

  private final String filePath;
}
