package oogasalad.Game.GameModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A general class that provides functions to get typed values of a map. Intended to be used in
 * conjunction with JSON.
 *
 * @author Jason Qiu
 */
class Properties {

  /**
   * Initializes with no entries.
   */
  public Properties() {
    properties = new HashMap<>();
  }

  /**
   * Creates and returns an instance of Properties from a JSON file.
   *
   * @param filePath the path to the JSON file.
   * @return an {@link Optional} describing the created instance of {@link Properties}.
   */
  public static Optional<Properties> load(String filePath) {
    Gson gson = new Gson();
    Properties loaded = null;
    try {
      loaded = gson.fromJson(filePath, Properties.class);
    } catch (JsonSyntaxException e) {
      // LOG MESSAGES AND HANDLE ERROR
    }
    return Optional.ofNullable(loaded);
  }

  /**
   * Returns the raw string value of the property if found.
   *
   * @param key the key of the property to access.
   * @return an {@link Optional} describing the property's value.
   */
  public Optional<String> getString(String key) {
    // TODO: MAYBE THROW A KEYNOTFOUND ERROR INSTEAD SO THE CALLER KNOWS
    return properties.get(key).describeConstable();
  }

  /**
   * Returns the boolean representation of the property's value.
   *
   * @param key the key of the property to access.
   * @return an {@link Optional} describing the boolean representation of the property's value.
   */
  public Optional<Boolean> getBoolean(String key) {
    return getString(key).map(rawString -> {
      if (rawString.equals("true")) {
        return true;
      }
      if (rawString.equals("false")) {
        return false;
      }
      // TODO: LOG MESSAGES AND HANDLE VALIDATION ERROR
      return null;
    });
  }

  /**
   * Returns the double representation of the property's value.
   *
   * @param key the key of the property to access.
   * @return an {@link Optional} describing the double representation of the property's value.
   */
  public Optional<Double> getDouble(String key) {
    try {
      return getString(key).map(Double::parseDouble);
    } catch (NumberFormatException e) {
      // TODO: LOG MESSAGES AND HANDLE VALIDATION ERROR
      return Optional.empty();
    }
  }

  /**
   * Returns the integer representation of the property's value.
   *
   * @param key the key of the property to access.
   * @return an {@link Optional} describing the integer representation of the property's value.
   */
  public Optional<Integer> getInteger(String key) {
    try {
      return getString(key).map(Integer::parseInt);
    } catch (NumberFormatException e) {
      // TODO: LOG MESSAGES AND HANDLE VALIDATION ERROR
      return Optional.empty();
    }
  }

  /**
   * Updates a property only if it already exists.
   *
   * @param key   the queried key.
   * @param value the value to set.
   * @return false if the key does not exist, otherwise true.
   */
  public boolean update(String key, String value) {
    if (!properties.containsKey(key)) {
      return false;
    }
    properties.put(key, value);
    return true;
  }

  private final Map<String, String> properties;
}
