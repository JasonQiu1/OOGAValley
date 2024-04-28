package oogasalad.view.login;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtility {

  private static final String GAME_SAVES_DIRECTORY = "data/gamesaves/";
  private static final String GAME_CONFIGURATIONS_DIRECTORY = "data/gameconfigurations/";
  private static final String CONFIGURABLE_STORES_DIRECTORY = "data/configurablestores/";

  public static void saveJsonToFile(int id, String gameSaveJson, String gameConfigJson,
      String storeJson) throws IOException {
    System.out.println("Saving game with id: " + id);
    writeToFile(GAME_SAVES_DIRECTORY, "" + id + ".json", gameSaveJson);
    writeToFile(GAME_CONFIGURATIONS_DIRECTORY, "" + id + ".json", gameConfigJson);
    writeToFile(CONFIGURABLE_STORES_DIRECTORY, "" + id + ".json", storeJson);
  }

  private static void writeToFile(String directoryPath, String fileName, String jsonContent)
      throws IOException {
    File directory = new File(directoryPath);
    File file = new File(directory, fileName);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(jsonContent);
    }
  }

  public static String[] readFileAsString(int id) throws IOException {
    String[] result = new String[3];
    result[0] = new String(Files.readAllBytes(Paths.get(GAME_SAVES_DIRECTORY + id + ".json")));
    result[1] = new String(
        Files.readAllBytes(Paths.get(GAME_CONFIGURATIONS_DIRECTORY + id + ".json")));
    result[2] = new String(
        Files.readAllBytes(Paths.get(CONFIGURABLE_STORES_DIRECTORY + id + ".json")));
    return result;
  }
}
