package oogasalad.model;

import java.util.Map;
import oogasalad.model.exception.KeyNotFoundException;

public interface ReadOnlyGameConfigurablesStore {

  /**
   * Gets the read-only version of a configurable (GameObject, Item, etc.) that exists in this game
   * configuration.
   *
   * @param id the id of the configurable.
   * @return the ReadOnlyProperties of the configurable.
   * @throws KeyNotFoundException if the id does not exist.
   */
  ReadOnlyProperties getConfigurableProperties(String id) throws KeyNotFoundException;

  /**
   * Returns a raw unmodifiable map of the configurables.
   *
   * @return a raw unmodifiable map of the configurables.
   */
  Map<String, ReadOnlyProperties> getAllConfigurables();
}
