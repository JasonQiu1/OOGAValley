package oogasalad.view.playing.component;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import oogasalad.model.gameplay.GameTime;
import oogasalad.model.gameplay.GameTimeInterface;
import oogasalad.model.gameplay.PlantModel;
import oogasalad.view.playing.PlayingPageView;

/**
 * A 2d grid representing the land and the plants (buildings).
 */
public class LandView {

  private final GridPane landGridPane;

  private final List<PlantView> plantViewList;
  private final BagView bagView;
  private final TopAnimationView topAnimationView;
  private final Pile[][] piles;

  private final SelectedItem selectedItem;

  private final GameTime gameTime;
  /**
   * Initialize a land with some plants and lands already defined.
   *
   * @param plantModelList the plants to be grown
   * @param gameTime       the current game time
   * @param selectedItem   the selected item used later for checking if the tool can work on the
   *                       tile
   */
  public LandView(List<PlantModel> plantModelList, GameTime gameTime, SelectedItem selectedItem,
      BagView bagView, TopAnimationView topAnimationView) {
    double height = PlayingPageView.landGridPaneHeight;
    double width = PlayingPageView.landGridPaneWidth;
    int column = PlayingPageView.landNumCols;
    int row = PlayingPageView.landNumRows;
    landGridPane = new GridPane();
    landGridPane.setPrefHeight(height);
    landGridPane.setPrefWidth(width);
    plantViewList = new ArrayList<>();
    piles = new Pile[column][row];
    this.selectedItem = selectedItem;
    this.gameTime = gameTime;
    double widthSingle = width / column;
    double heightSingle = height / row;
    for (int i = 0; i < column; i++) {
      for (int j = 0; j < row; j++) {
        Land land = new Land(widthSingle, heightSingle, i, j, "img/grass.jpg");
        Pile p = new Pile(null, land, this, i, j);
        p.setId("pile" + i + j);
        this.piles[i][j] = p;
        this.landGridPane.add(p, i, j);
      }
    }
    for (PlantModel p : plantModelList) {
      PlantView plantView = new PlantView(p, heightSingle, widthSingle);
      this.piles[p.getX()][p.getY()].setPlantView(plantView);
      plantViewList.add(plantView);
    }
    this.bagView = bagView;
    this.topAnimationView = topAnimationView;
  }

  /**
   * Update the plants according to game Time.
   *
   * @param gameTime
   */
  public void update(GameTimeInterface gameTime) {
    for (PlantView plantView : plantViewList) {
      plantView.updateImage(gameTime);
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

  /**
   * Set the onclick method for a chosen item.
   *
   * @param pile the pile to be updated
   */
  public void check(Pile pile, MouseEvent event) {
    if (selectedItem.getSelected().equals("img/panda.png")) {
      if (pile.getPlantView() == null) {
        PlantModel plantModel = new PlantModel.Builder().setPlantedTime(gameTime.copy())
            .setMatureTime(new GameTime(0, 0, 1))
            .setStatusImagePath(new String[]{"img/half_panda.png", "img/panda.png"})
            .setItemUrl("img/wheat.png").setToolUrl("img/tool.png")
            .setX(pile.getX()).setY(pile.getY()).build();
        PlantView p = new PlantView(plantModel, pile.getHeight(), pile.getWidth());
        pile.setPlantView(p);
        plantViewList.add(p);
      }
    } else if (selectedItem.getSelected().equals("img/tool.png")) {
      if (pile.getPlantView() != null &&
          pile.getPlantView().getProgress(gameTime) == 1.0 &&
          pile.getPlantView().getToolUrl().equals(selectedItem.getSelected())) {
        plantViewList.remove(pile.getPlantView());
        BagItem newBagItem = new BagItem(pile.getPlantView().getItemUrl(),
            PlayingPageView.bottomCellWidth,
            PlayingPageView.bottomCellHeight, selectedItem, 1);
        topAnimationView.collectItemAnimation(newBagItem,
            event.getSceneX() - PlayingPageView.windowWidth / 2,
            event.getSceneY() - PlayingPageView.windowHeight / 2,
            bagView.getAddRealLocation(newBagItem)[1],
            bagView.getAddRealLocation(newBagItem)[0], 3.0);

        pile.removePlant();
      }
    }
  }
}
