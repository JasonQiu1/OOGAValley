package oogasalad.Game.GameModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oogasalad.Game.GameModel.exception.KeyNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// MAKE SURE THIS HAS THE SAME FIELDS AS PROPERTIES
class MockProperties {

  public MockProperties() {
    this.properties = new HashMap<>();
    this.listProperties = new HashMap<>();
    this.mapProperties = new HashMap<>();
  }

  public MockProperties(Map<String, String> properties, Map<String, List<String>> listProperties,
      Map<String, Map<String, String>> mapProperties) {
    this.properties = properties;
    this.listProperties = listProperties;
    this.mapProperties = mapProperties;
  }

  public final Map<String, String> properties;
  public final Map<String, List<String>> listProperties;
  public final Map<String, Map<String, String>> mapProperties;
  private static final DataFactory<MockProperties> FACTORY =
      new DataFactory<>(MockProperties.class);
  private static final Logger LOG = LogManager.getLogger(Properties.class);
}


class DataFactoryTest {

  public DataFactory<Properties> propertiesDataFactory;
  public DataFactory<MockProperties> mockPropertiesDataFactory;
  public MockProperties emptyProperties;
  public MockProperties filledProperties;
  private static final String TEST_DATA_DIRECTORY = "test";

  @BeforeEach
  void setUp() {
    propertiesDataFactory = new DataFactory<>(Properties.class);
    mockPropertiesDataFactory = new DataFactory<>(MockProperties.class);
    emptyProperties = new MockProperties();

    Map<String, String> mockValues =
        Map.of("key1", "val1", "key2", "123", "key3", "false", "key4", "True");
    Map<String, List<String>> mockListValues =
        Map.of("key1", List.of("val1", "val2", "val3"), "key2", List.of("1", "2", "3"));
    Map<String, Map<String, String>> mockMapValues =
        Map.of("key1", mockValues, "key2", Map.of("k1", "2"));
    filledProperties = new MockProperties(mockValues, mockListValues, mockMapValues);
  }

  @Test
  void saveEmpty() {
    String dataFilePath = "emptyPropertiesTest";
    deleteFile(dataFilePath);
    Assertions.assertDoesNotThrow(() -> save(dataFilePath, emptyProperties));
  }

  @Test
  void saveFilled() {
    String dataFilePath = "filledPropertiesTest";
    deleteFile(dataFilePath);
    Assertions.assertDoesNotThrow(() -> save(dataFilePath, filledProperties));
  }

  @Test
  void loadEmpty() throws IOException {
    // GIVEN
    String dataFilePath = "emptyPropertiesTest";
    deleteFile(dataFilePath);
    save(dataFilePath, emptyProperties);

    Properties loaded = load(dataFilePath);
    assertThrows(KeyNotFoundException.class, () -> loaded.getString("key1"));
    assertThrows(KeyNotFoundException.class, () -> loaded.getStringList("key1"));
    assertThrows(KeyNotFoundException.class, () -> loaded.getStringMap("key1"));
  }

  @Test
  void loadFilled() throws IOException {
    // GIVEN
    String dataFilePath = "filledPropertiesTest";
    deleteFile(dataFilePath);
    save(dataFilePath, filledProperties);

    Properties loaded = load(dataFilePath);
    assertEquals(filledProperties.properties, loaded.getCopyOfProperties());
    assertEquals(filledProperties.listProperties, loaded.getCopyOfListProperties());
    assertEquals(filledProperties.mapProperties, loaded.getCopyOfMapProperties());
  }

  void save(String dataFilePath, MockProperties properties) throws IOException {
    mockPropertiesDataFactory.save(Paths.get(TEST_DATA_DIRECTORY, dataFilePath).toString(),
        properties);
  }

  Properties load(String dataFilePath) throws IOException {
    return propertiesDataFactory.load(Paths.get(TEST_DATA_DIRECTORY, dataFilePath).toString());
  }

  private void deleteFile(String dataFilePath) {
    File dataFile = new File(DataFactory.DATA_DIRECTORY + TEST_DATA_DIRECTORY,
        addDataFileExtension(dataFilePath));
    if (dataFile.exists()) {
      dataFile.delete();
    }
  }

  // makes sure the filePath ends with the given extension
  private String addDataFileExtension(String filePath) {
    if (filePath.endsWith("." + DataFactory.DATA_FILE_EXTENSION)) {
      return filePath;
    }
    return filePath + "." + DataFactory.DATA_FILE_EXTENSION;
  }
}