package oogasalad.view.playing;

import java.lang.module.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.model.gameplay.Game;

public class TempPlaying extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    GameConfiguration gameConfiguration = new GameConfiguration();
    gameConfiguration.save("TempGameConfiguration.json");
    Game game = new Game("TempGameConfiguration.json");
    System.out.println(game.getGameConfiguration().getRules());
    System.out.println();

  }
}
