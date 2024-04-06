package oogasalad.Game.GameModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.Game.GameModel.exception.BadGsonLoadException;
import oogasalad.Game.GameModel.exception.BadValueParseException;
import oogasalad.Game.GameModel.exception.KeyNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A general class that provides functions to get typed values of a map. Intended to be used in
 * conjunction with JSON.
 *
 * @author Jason Qiu
 */
public class Properties {

  /**
   * Initializes with no entries.
   */
  public Properties() {
    properties = new HashMap<>();
    listProperties = new HashMap<>();
    mapProperties = new HashMap<>();
  }

  /**
   * Creates and returns an instance of {@link Properties} from a JSON file.
   *
   * @param dataFilePath the path to the JSON file starting from inside the data directory.
   * @return the created instance of {@link Properties}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link Properties}
   */
  public static Properties of(String dataFilePath) throws BadGsonLoadException {
    Gson gson = new Gson();
    try {
      return gson.fromJson(dataFilePath, Properties.class);
    } catch (JsonSyntaxException e) {
      LOG.error("Couldn't load `{}` as an instance of Properties using Gson.", dataFilePath);
      throw new BadGsonLoadException(dataFilePath, Properties.class.getSimpleName(), e);
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
    throwIfKeyNotFound(properties, key);
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
      default -> {
        LOG.error("Couldn't parse value '{}' as a boolean (key = {}).", rawValue, key);
        throw new BadValueParseException(rawValue, Boolean.class.getSimpleName());
      }
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
      LOG.error("Couldn't parse value '{}' as a double (key = {}).", rawValue, key);
      throw new BadValueParseException(rawValue, Double.class.getSimpleName(), e);
    }
  }

  /**
   * Returns the raw string list value of the property if found.
   *
   * @param key the key of the property to access.
   * @return the property's value's raw string list.
   * @throws KeyNotFoundException if the key does not exist.
   */
  public List<String> getStringList(String key) throws KeyNotFoundException {
    throwIfKeyNotFound(listProperties, key);
    return listProperties.get(key);
  }

  /**
   * Returns the raw string map value of the property if found.
   *
   * @param key the key of the property to access.
   * @return the property's value's raw string map.
   * @throws KeyNotFoundException if the key does not exist.
   */
  public Map<String, String> getStringMap(String key) throws KeyNotFoundException {
    throwIfKeyNotFound(mapProperties, key);
    return mapProperties.get(key);
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
      LOG.error("Couldn't parse value '{}' as an integer (key = {}).", rawValue, key);
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
    throwIfKeyNotFound(properties, key);
    properties.put(key, value);
  }

  /**
   * Updates a property only if it already exists. For list values.
   *
   * @param key  the queried key.
   * @param list the list to set the key to.
   * @throws KeyNotFoundException if the key does not exist.
   */
  public void update(String key, List<String> list) throws KeyNotFoundException {
    throwIfKeyNotFound(listProperties, key);
    listProperties.put(key, List.copyOf(list));
  }

  /**
   * Updates a property only if it already exists. For map values.
   *
   * @param key the queried key.
   * @param map the map to set the key to.
   * @throws KeyNotFoundException if the key does not exist.
   */
  public void update(String key, Map<String, String> map) throws KeyNotFoundException {
    throwIfKeyNotFound(mapProperties, key);
    mapProperties.put(key, Map.copyOf(map));
  }

  private final Map<String, String> properties;
  private final Map<String, List<String>> listProperties;
  private final Map<String, Map<String, String>> mapProperties;
  private static final Logger LOG = LogManager.getLogger(Properties.class);

  private void throwIfKeyNotFound(Map<String, ?> map, String key) throws KeyNotFoundException {
    if (!map.containsKey(key)) {
      LOG.error("Couldn't find key '{}'.", key);
      throw new KeyNotFoundException(key);
    }
  }
}
