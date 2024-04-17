package oogasalad.view.playing;

import javafx.application.Application;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.gameplay.Game;

public class TempPlaying extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    //Game game = new Game("TempGameConfiguration.json");
    //System.out.println(game.getGameConfiguration().getRules());
    GameConfiguration gameConfiguration = new GameConfiguration();
    gameConfiguration.save("TempGameConfiguration.json");
  }
}
