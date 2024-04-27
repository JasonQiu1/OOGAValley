package oogasalad.view.buttonmenu;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    VBox root = new VBox();

    SplashUtils.createButtonsFromFile(buttonsFilePath, primaryStage, root, primaryLanguage, previousScene);

    menuScene = new Scene(root);
    menuStage.setScene(menuScene);
    menuStage.show();


  }

}
