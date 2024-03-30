package oogasalad;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import oogasalad.view.ViewablePng;
import oogasalad.view.exception.FileNotPngException;
import org.junit.jupiter.api.Test;

public class ViewablePngTest {

  @Test
  void testViewablePngIfUrlIsNull() {
    String url = null;
    assertThrows(FileNotFoundException.class, () -> {
      new ViewablePng(url);
    });
  }

  @Test
  void testViewablePngIfUrlIsValid()
      throws FileNotPngException, FileNotFoundException {
    String url = "./data/rock.png";
    new ViewablePng(url);
  }

  @Test
  void testFileDoesNotExist() {
    String url = "./data/file/not/exist";
    assertThrows(FileNotFoundException.class, () -> {
      new ViewablePng(url);
    });
  }

  @Test
  void testFileIsNotPng() {
    String url = "./data/FOLDER_PURPOSE.md";
    assertThrows(FileNotPngException.class, () -> {
      new ViewablePng(url);
    });
  }

}
