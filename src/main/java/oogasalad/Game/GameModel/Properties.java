package oogasalad.Game.GameModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.Map;
import oogasalad.Game.GameModel.exception.BadGsonLoadException;
import oogasalad.Game.GameModel.exception.BadValueParseException;
import oogasalad.Game.GameModel.exception.KeyNotFoundException;

/**
 * A general class that provides functions to get typed values of a map. Intended to be used in
 * conjunction with JSON.
 *
 * @author Jason Qiu
 */
public class Properties {

  private final Map<String, String> properties;

  /**
   * Initializes with no entries.
   */
  public Properties() {
    properties = new HashMap<>();
  }

  /**
   * Creates and returns an instance of {@link Properties} from a JSON file.
   *
   * @param filePath the path to the JSON file.
   * @return the created instance of {@link Properties}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link Properties}
   */
  public static Properties of(String filePath) throws BadGsonLoadException {
    Gson gson = new Gson();
    try {
      return gson.fromJson(filePath, Properties.class);
    } catch (JsonSyntaxException e) {
      // TODO: LOG MESSAGES AND HANDLE ERROR
      throw new BadGsonLoadException(filePath, Properties.class.getSimpleName(), e);
    }
  }

  /**
   * Returns the raw string value of the property if found.
   *
   * @param key the key of the property to access.
   * @return the property's value's raw string.
   * @throws KeyNotFoundException if the key does not exist.
   */
  public String getString(String key) throws KeyNotFoundException {
    if (!properties.containsKey(key)) {
      throw new KeyNotFoundException(key);
    }
    return properties.get(key);
  }

  /**
   * Returns the boolean representation of the property's value.
   *
   * @param key the key of the property to access.
   * @return the boolean representation of the property's value.
   * @throws KeyNotFoundException   if the key does not exist.
   * @throws BadValueParseException if the string value cannot be parsed into a boolean.
   */
  public boolean getBoolean(String key) throws KeyNotFoundException, BadValueParseException {
    final String rawValue = getString(key);

    return switch (rawValue.toLowerCase()) {
      case "true" -> true;
      case "false" -> false;
      // TODO: LOG MESSAGES AND HANDLE VALIDATION ERROR
      default -> throw new BadValueParseException(rawValue, Boolean.class.getSimpleName());
    };
  }

  /**
   * Returns the double representation of the property's value.
   *
   * @param key the key of the property to access.
   * @return the double representation of the property's value.
   * @throws KeyNotFoundException   if the key does not exist.
   * @throws BadValueParseException if the string value cannot be parsed into a double.
   */
  public double getDouble(String key) throws KeyNotFoundException, BadValueParseException {
    final String rawValue = getString(key);
    try {
      return Double.parseDouble(rawValue);
    } catch (NumberFormatException e) {
      // TODO: LOG MESSAGES AND HANDLE VALIDATION ERROR
      throw new BadValueParseException(rawValue, Double.class.getSimpleName(), e);
    }
  }

  /**
   * Returns the integer representation of the property's value.
   *
   * @param key the key of the property to access.
   * @return the integer representation of the property's value.
   * @throws KeyNotFoundException   if the key does not exist.
   * @throws BadValueParseException if the string value cannot be parsed into an integer.
   */
  public int getInteger(String key) throws KeyNotFoundException, BadValueParseException {
    final String rawValue = getString(key);
    try {
      return Integer.parseInt(rawValue);
    } catch (NumberFormatException e) {
      // TODO: LOG MESSAGES AND HANDLE VALIDATION ERROR
      throw new BadValueParseException(rawValue, Integer.class.getSimpleName(), e);
    }
  }

  /**
   * Updates a property only if it already exists.
   *
   * @param key   the queried key.
   * @param value the value to set.
   * @throws KeyNotFoundException if the key does not exist.
   */
  public void update(String key, String value) throws KeyNotFoundException {
    if (!properties.containsKey(key)) {
      throw new KeyNotFoundException(key);
    }
    properties.put(key, value);
  }
}
