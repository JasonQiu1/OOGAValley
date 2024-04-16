package oogasalad.model.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import oogasalad.model.api.exception.BadGsonLoadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory for serializing and deserializing data objects to and from the data directory.
 * <p>
 * This should be the only class that depends on Gson.
 *
 * @param <T> The object type to serialize/deserialize.
 * @author Jason Qiu
 */
public class DataFactory<T> {

  // TODO: Maybe externalize this to a config? I can't see this directory ever changing though.
  public static final String DATA_DIRECTORY = "data";
  public static final String DATA_FILE_EXTENSION = "json";
  private static final Gson GSON =
      new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
          .serializeNulls()
          // LENIENT MAY INTRODUCE BUGS, BUT ALSO MAKES MANUALLY EDITING DATA FILES MORE FORGIVING
          .setLenient().create();
  private static final Logger LOG = LogManager.getLogger(DataFactory.class);
  private final Class<T> clazz;

  /**
   * Initialization.
   *
   * @param clazz the class that the DataFactory is (de)serializing instances of.
   */
  public DataFactory(Class<T> clazz) {
    this.clazz = clazz;
  }

  /**
   * Creates and returns an instance of {@link Properties} from a JSON file.
   *
   * @param dataFilePath the path to the JSON file starting from inside the data directory.
   * @return the created instance of {@link Properties}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of { @link
   *                              Properties}
   * @throws IOException          if the filePath could not be opened.
   */
  public T load(String dataFilePath) throws BadGsonLoadException, IOException {
    File dataFile = new File(DATA_DIRECTORY, addDataFileExtension(dataFilePath));
    try (Reader dataReader = new FileReader(dataFile)) {
      return GSON.fromJson(dataReader, clazz);
    } catch (JsonSyntaxException e) {
      LOG.error("Couldn't load `{}` as an instance of {} using Gson.", dataFile.toString(),
          clazz.getTypeName());
      throw new BadGsonLoadException(dataFile.toString(), clazz.toString(), e);
    } catch (IOException e) {
      LOG.error("Error writing to '{}' when trying to deserialize as class '{}'.",
          dataFile.getAbsolutePath(), clazz.toString());
      throw e;
    }
  }

  /**
   * Serializes an object to the JSON file located at data/{dataFilePath}.
   *
   * @param dataFilePath the path of the file to write to starting from the data directory as root.
   * @param object       the object to serialize.
   * @throws IOException if the given file path cannot be created, opened, or written to.
   */
  public void save(String dataFilePath, T object) throws IOException {
    File dataFile = new File(DATA_DIRECTORY, addDataFileExtension(dataFilePath));
    try (Writer writer = new FileWriter(dataFile, false)) {
      writer.write(GSON.toJson(object));
    } catch (IOException e) {
      LOG.error("Error writing to '{}' when trying to serialize object '{}'.",
          dataFile.getAbsolutePath(), object.toString());
      throw e;
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
