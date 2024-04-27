package oogasalad.view.buttonmenu;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.start.SplashUtils;
import oogasalad.view.start.StartScreen;

public class ButtonMenu {
  private Scene menuScene;
  private Stage menuStage;
  private Stage primaryStage;
  private Scene previousScene;
  private String primaryLanguage;
  private String buttonsFilePath;

  private String STYLES = "/view/buttonMenu/menu_style.css";

  public ButtonMenu(Stage mainStage, String language, Scene backScene, String filePath) {
    primaryStage = mainStage;
    primaryLanguage = language;
    previousScene = backScene;
    buttonsFilePath = filePath;


    primaryStage.sceneProperty().addListener(new ChangeListener<Scene>() {
      @Override
      public void changed(ObservableValue<? extends Scene> observable, Scene oldValue,
          Scene newValue) {
        menuStage.close();
      }
    });
  }

  public void open() {
    menuStage = new Stage();
//    menuScene(StageStyle.UNDECORATED);
    VBox root = new VBox();
    root.setSpacing(10);

    SplashUtils.createButtonsFromFile(buttonsFilePath, primaryStage, root, primaryLanguage, previousScene);

    ScrollPane scrollPane = new ScrollPane(root);
    menuScene = new Scene(root);

    menuScene.getStylesheets().add(getClass().getResource(STYLES).toExternalForm());

    menuStage.setScene(menuScene);
    menuStage.show();


  }

}
