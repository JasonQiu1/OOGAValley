package oogasalad.Game.GameModel.exception;

/**
 * Thrown when Gson fails to parse a json file into the given class.
 *
 * @author Jason Qiu
 */
public class BadGsonLoadException extends RuntimeException {

  private final String filePath;
  private final String className;

  public BadGsonLoadException(String filePath, String className) {
    super(BadGsonLoadException.class.getSimpleName());
    this.filePath = filePath;
    this.className = className;
  }

  public BadGsonLoadException(String filePath, String className, Exception exception) {
    super(BadGsonLoadException.class.getSimpleName(), exception);
    this.filePath = filePath;
    this.className = className;
  }

  public String getFilePath() {
    return filePath;
  }

  public String getClassName() {
    return className;
  }
}
