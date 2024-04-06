package oogasalad.Game.GameModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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

  /**
   * Creates and returns an instance of {@link Properties} from a JSON file.
   *
   * @param dataFilePath the path to the JSON file starting from inside the data directory.
   * @return the created instance of {@link Properties}.
   * @throws BadGsonLoadException if the filePath is unable to be parsed into an instance of
   *                              {@link Properties}
   */
  public T load(String dataFilePath, Class<T> clazz) throws BadGsonLoadException {
    File dataFile = new File(DATA_DIRECTORY, dataFilePath);
    try (Reader dataReader = new FileReader(dataFile)) {
      return new Gson().fromJson(dataReader, clazz);
    } catch (JsonSyntaxException | IOException e) {
      LOG.error("Couldn't load `{}` as an instance of {} using Gson.", dataFile.toString(), clazz.getSimpleName());
      throw new BadGsonLoadException(dataFile.toString(), clazz.getSimpleName(), e);
    }
  }

  // TODO: Maybe externalize this to a config? I can't see this directory ever changing though.
  private static final String DATA_DIRECTORY = "data";

  private static final Logger LOG = LogManager.getLogger(DataFactory.class);
}
