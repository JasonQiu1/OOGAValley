package oogasalad.view.start;

import java.io.File;
import java.util.Optional;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserContainer extends BorderPane {

  private final FileChooser fileChooser;

  public FileChooserContainer(String title, String defaultFolderPath) {
    super();
    fileChooser = new FileChooser();
    fileChooser.setTitle(title);
    fileChooser.setInitialDirectory(new File(defaultFolderPath));

    setId(title);
  }

  public FileChooser getFileChooser() {
    return fileChooser;
  }

  public Optional<File> showFileChooserDialog(Stage stage) {
    File selectedFile = fileChooser.showOpenDialog(stage);
    return Optional.ofNullable(selectedFile);
  }

  public String getFileChooserTitle() {
    return fileChooser.getTitle();
  }
}