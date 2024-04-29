package oogasalad.view.start;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oogasalad.view.playing.PlayingPageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoaderListDisplay {

  private static final Logger LOG = LogManager.getLogger(LoaderListDisplay.class);
  private static final String DEFAULT_RESOURCE_PACKAGE = "view.start.LoaderListDisplay.";
  private static final String DEFAULT_CONFIG_FOLDER = "data/gameconfigurations";
  private static final String DEFAULT_SAVES_FOLDER = "data/gamesaves";
  private static final String STYLES = "/styles.css";
  private File selectedSaveFile;
  private File selectedConfigFile;
  private final Stage primaryStage;
  private final ResourceBundle propertiesBundle;
  private Stage myStage;
  private ListView<String> saveView;
  private ListView<String> configView;

  public LoaderListDisplay(Stage mainStage, String language, String title) {
    primaryStage = mainStage;
    propertiesBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    myStage = new Stage();
    myStage.setTitle(title);
  }

  public File[] open() {
    saveView = returnItemListView(DEFAULT_SAVES_FOLDER);
    configView = returnItemListView(DEFAULT_CONFIG_FOLDER);


    VBox saveBox = viewBoxMaker(saveView, propertiesBundle.getString("save_files"));
    VBox configBox = viewBoxMaker(configView, propertiesBundle.getString("config_files"));

    HBox fileLists = new HBox(saveBox, configBox);


    setupBottom(fileLists);

    File[] files = {selectedSaveFile, selectedConfigFile};
    return Arrays.copyOf(files, files.length);

  }

  public File openConfig() {
    configView = returnItemListView(DEFAULT_CONFIG_FOLDER);

    HBox fileList = new HBox(viewBoxMaker(configView, propertiesBundle.getString("config_files")));
    setupBottom(fileList);
    return selectedSaveFile;
  }

  private VBox viewBoxMaker (ListView<String> view, String title) {
    return new VBox(new Label(title), view);
  }

  private void setupBottom(HBox fileLists) {
    Button selectButton = new Button(propertiesBundle.getString("load"));
    selectButton.getStyleClass().add("load");
    selectButton.setOnAction(event -> selectSaveAndConfigFiles(saveView, configView));

    Button exitButton = new Button(propertiesBundle.getString("close"));
    exitButton.setOnAction(event -> myStage.close());

    HBox hBox = new HBox(selectButton, exitButton);
    hBox.setAlignment(Pos.CENTER);

    VBox vBox = new VBox(fileLists, hBox);
    vBox.setId("loader_vBox");

    vBox.setPrefSize(400, 400);
    ScrollPane scrollPane = new ScrollPane(vBox);
    scrollPane.setId("loader_scrollpane");


    Scene scene = new Scene(scrollPane);
    scene.getStylesheets().add(getClass().getResource(STYLES).toExternalForm());

    myStage.initStyle(StageStyle.UNDECORATED);
    myStage.initOwner(primaryStage);

    myStage.setScene(scene);
    myStage.showAndWait();
  }

  private void selectSaveAndConfigFiles(ListView<String> saveList, ListView<String> configList) {
    selectedSaveFile = selectFile(DEFAULT_SAVES_FOLDER, saveList);
    selectedConfigFile = selectFile(DEFAULT_CONFIG_FOLDER, configList);

    LOG.debug(selectedConfigFile);
    LOG.debug(selectedSaveFile);

    Stage stage = (Stage) saveList.getScene().getWindow();
    stage.close();

  }
  private File selectFile(String defaultDirectoryPath, ListView<String> listView) {
    String selectedFileName = listView.getSelectionModel().getSelectedItem();

    if (selectedFileName != null) {
       return new File(defaultDirectoryPath + "/" + selectedFileName);
//      Stage stage = (Stage) listView.getScene().getWindow();
//      stage.close();
    }

    return null;
  }

  // TODO: update the tests for this
  private ListView<String> returnItemListView(String directoyPath) {
    ListView<String> listView = new ListView<String>();
    listView.getStyleClass().add("list_view");
    listView.setId("list_view");

    File directory = new File(directoyPath);
    File[] files = directory.listFiles();

    if (files != null) {
      for (File file : files) {
        listView.getItems().add(file.getName());
      }
    }

    return listView;
  }



}
