package oogasalad.view.playing;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayingView extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL url = new File("src/main/resources/fxml/playing_page.fxml").toURI().toURL();
    Parent root = FXMLLoader.load(url);
    primaryStage.setTitle("Playing Mode");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
