package oogasalad.view.start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.stage.FileChooser;
import oogasalad.view.start.FileChooserContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileChooserContainerTest {
  private static final String DEFAULT_RESOURCE_FOLDER = "src/main/resources/";
  FileChooserContainer fileChooserContainer;
  @BeforeEach
  public void setup() {
    fileChooserContainer = new FileChooserContainer("Title", DEFAULT_RESOURCE_FOLDER);
  }


  @Test
  public void testFileChooserTitle() {
    assertTrue(fileChooserContainer.getFileChooserTitle().equals("Title"));
  }

  @Test
  public void testFileChooserClass() {
    assertEquals(fileChooserContainer.getFileChooser().getClass(), FileChooser.class);
  }


}
