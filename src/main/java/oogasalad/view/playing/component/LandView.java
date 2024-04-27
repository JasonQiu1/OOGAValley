package oogasalad.view.playing.component;

import javafx.scene.layout.GridPane;
import oogasalad.model.api.ReadOnlyGameWorld;

/**
 * A 2d grid representing the land and the plants (buildings).
 */
public class LandView {


  private final GridPane landGridPane = new GridPane();

  private final Pile[][] piles;

  private final ReadOnlyGameWorld readOnlyGameWorld;


  public LandView(ReadOnlyGameWorld readOnlyGameWorld) {
    this.readOnlyGameWorld = readOnlyGameWorld;
    piles = new Pile[readOnlyGameWorld.getHeight()][readOnlyGameWorld.getWidth()];
    for (int i = 0; i < piles.length; i++) {
      for (int j = 0; j < piles[0].length; j++) {
        piles[i][j] = new Pile();
        landGridPane.add(piles[i][j], j, i);
      }
    }
  }

  /**
   * Update the landView
   */
  public void update() {
    for (int i = 0; i < readOnlyGameWorld.getHeight(); i++) {
      for (int j = 0; j < readOnlyGameWorld.getWidth(); j++) {
        piles[i][j].update(readOnlyGameWorld.getImagePath(j, i, 0));
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
