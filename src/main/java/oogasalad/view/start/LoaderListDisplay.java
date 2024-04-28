package oogasalad.view.start;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;
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

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.start.LoaderListDisplay.";
  private final String defaultDirectoryPath;
  private File selectedFile;
  private Stage primaryStage;
  private ResourceBundle propertiesBundle;
  private Stage myStage;
  private ListView<String> listView;

  public LoaderListDisplay(Stage mainStage, String language, String defaultFolderPath) {
    primaryStage = mainStage;
    propertiesBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    defaultDirectoryPath = defaultFolderPath;
  }

  public Optional<File> open() {
    myStage = new Stage();

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

    Button selectButton = new Button(propertiesBundle.getString("load"));
    selectButton.getStyleClass().add("load");
    selectButton.setOnAction(event -> selectFile(listView));

    Button exitButton = new Button (propertiesBundle.getString("close"));
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
