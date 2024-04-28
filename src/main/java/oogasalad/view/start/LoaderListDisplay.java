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
  private Stage myStage;
  ListView<String> listView;

  public LoaderListDisplay(Stage mainStage, String title, String defaultFolderPath) {
    primaryStage = mainStage;
    vBox = new VBox();
    defaultDirectoryPath = defaultFolderPath;
    myTitle = title;
  }

  public Optional<File> open() {
    myStage = new Stage();
    myStage.setTitle(myTitle);

    listView = new ListView<>();
    listView.getStyleClass().add("list_view");
    listView.setId("list_view");

    File directory = new File(defaultDirectoryPath);
    File[] files = directory.listFiles();

    if (files != null) {
      for (File file : files) {
        listView.getItems().add(file.getName());
      }
    }

    Button selectButton = new Button("Load");
    selectButton.getStyleClass().add("load");
    selectButton.setOnAction(event -> selectFile(listView));

    Button exitButton = new Button ("Close");
    exitButton.setOnAction(event -> myStage.close());

    HBox hBox = new HBox(selectButton, exitButton);
    hBox.setAlignment(Pos.CENTER);

    VBox vBox = new VBox(listView, hBox);
    vBox.setId("loader_vBox");

    vBox.setPrefSize(400, 400);
    ScrollPane scrollPane = new ScrollPane(vBox);
    scrollPane.setId("loader_scrollpane");

    Scene scene = new Scene(scrollPane);

    myStage.initStyle(StageStyle.UNDECORATED);
    myStage.initOwner(primaryStage);

    myStage.setScene(scene);
    myStage.showAndWait();


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

  public File getSelectedFile() {
    return selectedFile;
  }

  public ListView<String> getListView() {
    return listView;
  }


}
