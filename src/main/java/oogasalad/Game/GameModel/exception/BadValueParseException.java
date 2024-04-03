package oogasalad.Game.GameModel.exception;

public class BadValueParseException extends RuntimeException {
  public BadValueParseException(String value, String type) {
    super(BadValueParseException.class.getSimpleName());
    badValue = value;
    parseType = type;
  }
  public BadValueParseException(String value, String type, Exception exception) {
    super(BadValueParseException.class.getSimpleName(), exception);
    badValue = value;
    parseType = type;
  }

  public String getBadValue() {
    return badValue;
  }

  public String getParseType() {
    return parseType;
  }

  private final String badValue;
  private final String parseType;
}
