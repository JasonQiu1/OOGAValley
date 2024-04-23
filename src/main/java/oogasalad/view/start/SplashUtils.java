package oogasalad.view.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplashUtils {


  public static List<String[]> readCommaSeparatedCSVLines(String filename) {
    List<String[]> data = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(", ");
        data.add(values);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }

  public static String[] readCommaSeparatedCSV(String filename) {
    String[] values;
    try (BufferedReader br = new BufferedReader(new FileReader(filename))){
      values = br.readLine().split(", ");

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return values;
  }
}
