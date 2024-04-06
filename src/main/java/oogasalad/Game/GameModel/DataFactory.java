package oogasalad.Game.GameModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import oogasalad.Game.GameModel.exception.BadGsonLoadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory for serializing and deserializing data objects to and from the data directory.
 *
 * @param <T> The object type to serialize/deserialize.
 * @author Jason Qiu
 */
class DataFactory<T> {

  public DataFactory() {
    gson = new Gson();
  }

  /**
   * Creates and returns an instance of {@link Properties} from a JSON file.
   *
   * @param dataFilePath the path to the JSON file starting from inside the data directory.
   * @return the created instance of {@link Properties}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link Properties}
   */
  public T load(String dataFilePath, Class<T> clazz) throws BadGsonLoadException, IOException {
    File dataFile = new File(DATA_DIRECTORY, dataFilePath);
    try (Reader dataReader = new FileReader(dataFile)) {
      return gson.fromJson(dataReader, clazz);
    } catch (JsonSyntaxException e) {
      LOG.error("Couldn't load `{}` as an instance of {} using Gson.", dataFile.toString(),
          clazz.getSimpleName());
      throw new BadGsonLoadException(dataFile.toString(), clazz.getSimpleName(), e);
    } catch (IOException e) {
      LOG.error("Error writing to '{}' when trying to deserialize as class '{}'.",
          dataFile.getAbsolutePath(), clazz.getSimpleName());
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
    File dataFile = new File(DATA_DIRECTORY, dataFilePath);
    try (Writer writer = new FileWriter(dataFile, false)) {
      writer.write(gson.toJson(object));
    } catch (IOException e) {
      LOG.error("Error writing to '{}' when trying to serialize object '{}'.",
          dataFile.getAbsolutePath(), object.toString());
      throw e;
    }
  }

  private final Gson gson;
  // TODO: Maybe externalize this to a config? I can't see this directory ever changing though.
  private static final String DATA_DIRECTORY = "data";

  private static final Logger LOG = LogManager.getLogger(DataFactory.class);
}
