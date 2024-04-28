package oogasalad.view.start;

import java.io.File;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoaderListDisplay {

  private static final Logger LOG = LogManager.getLogger(LoaderListDisplay.class);

  private final String myTitle;
  private final String defaultDirectoryPath;
  private final VBox vBox;
  private File selectedFile;
  private Stage primaryStage;

  public LoaderListDisplay(Stage mainStage, String title, String defaultFolderPath) {
    primaryStage = mainStage;
    vBox = new VBox();
    defaultDirectoryPath = defaultFolderPath;
    myTitle = title;
  }

  public Optional<File> open(Stage stage) {
    stage.setTitle(myTitle);

    ListView<String> listView = new ListView<>();
    File directory = new File(defaultDirectoryPath);
    File[] files = directory.listFiles();

    if (files != null) {
      for (File file : files) {
        listView.getItems().add(file.getName());
      }
    }

    Button selectButton = new Button("Load");
    selectButton.setOnAction(event -> selectFile(listView));

    Button exitButton = new Button ("Close");
    exitButton.setOnAction(event -> stage.close());

    HBox hBox = new HBox(selectButton, exitButton);
    hBox.setAlignment(Pos.CENTER);

    VBox vBox = new VBox(listView, hBox);
    vBox.setPrefSize(400, 400);
    ScrollPane scrollPane = new ScrollPane(vBox);
    Scene scene = new Scene(scrollPane);

    stage.initStyle(StageStyle.UNDECORATED);
    stage.initOwner(primaryStage);

    stage.setScene(scene);
    stage.showAndWait();

    return Optional.ofNullable(selectedFile);
  }

  private void selectFile(ListView<String> listView) {
    String selectedFileName = listView.getSelectionModel().getSelectedItem();
    if (selectedFileName != null) {
      File pickedFile = new File(defaultDirectoryPath + "/" + selectedFileName);
      selectedFile = pickedFile;

      Stage stage = (Stage) listView.getScene().getWindow();
      stage.close();
    }
  }

}
