package oogasalad.view.playing;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The view for the playing page
 */
public class PlayingView extends Application {

  public PlayingView(){
    super();
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(
        new File("src/main/resources/fxml/playing_page.fxml").toURI().toURL());
    Parent root = fxmlLoader.load();
    PlayingPageController playingPageController = fxmlLoader.getController();
    playingPageController.setStage(primaryStage);
    primaryStage.setTitle("Playing Mode");
    primaryStage.setScene(new Scene(root));

    primaryStage.show();
  }
}
