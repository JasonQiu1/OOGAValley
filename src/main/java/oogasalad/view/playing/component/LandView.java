package oogasalad.view.playing.component;

import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;
import oogasalad.fake.GameInputHandler;
import oogasalad.fake.map.Coord;
import oogasalad.fake.map.GameMap;
import oogasalad.fake.object.Land;
import oogasalad.fake.object.Plant;

/**
 * A 2d grid representing the land and the plants (buildings).
 */
public class LandView {


  private final GridPane landGridPane = new GridPane();

  private final Pile[][] piles;

  private final GameMap gameMap;
  private final Map<Coord, Land> landPositionMap;
  private final Map<Coord, Plant> plantPositionMap;

  private final GameInputHandler gameInputHandler;


  public LandView(GameMap gameMap, GameInputHandler gameInputHandler) {
    this.gameInputHandler = gameInputHandler;
    this.gameMap = gameMap;
    this.landPositionMap = gameMap.getLandPositionMap();
    this.plantPositionMap = gameMap.getPlantPositionMap();
    piles = new Pile[gameMap.getHeight()][gameMap.getWidth()];
    for (int i = 0; i < piles.length; i++) {
      for (int j = 0; j < piles[0].length; j++) {
        piles[i][j] = new Pile();
        int finalI = i;
        int finalJ = j;
        piles[i][j].setOnMouseClicked(event -> {
          System.out.println(finalI);
          System.out.println(finalJ);
          gameInputHandler.interact(new Coord(finalI, finalJ));
        });
        landGridPane.add(piles[i][j], j, i);
      }
    }
  }

  public void update() {
    for (int i = 0; i < piles.length; i++) {
      for (int j = 0; j < piles[0].length; j++) {
        Coord coord = new Coord(i, j);
        Land land = landPositionMap.get(coord);
        Plant plant = plantPositionMap.get(coord);
        if (land == null) {
          continue;
        }
        List<String> listImagePath = new java.util.ArrayList<>(
            List.of(land.getLandConfig().getImagePath()));
        if (plant != null) {
          listImagePath.add(plant.getPlantConfig().getImagePath());
        }
        piles[i][j].update(listImagePath);
      }
    }
  }

  /**
   * Get the grid view to be displayed by javafx.
   *
   * @return the grid view to be displayed by javafx
   */
  public GridPane getGridView() {
    return landGridPane;
  }
}
