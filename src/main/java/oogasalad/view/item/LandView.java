package oogasalad.view.item;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import oogasalad.model.GameTime;
import oogasalad.model.api.GameTimeInterface;
import oogasalad.model.gameplay.PlantModel;
import oogasalad.view.playing.PlayingPageView;

/**
 * A 2d grid representing the land and the plants (buildings).
 */
public class LandView {

  private final GridPane landGridPane;

  private final List<PlantView> plantViewList;
  private final BagItemView bagItemView;
  private final TopAnimationView topAnimationView;
  private Pile[][] piles;

  private SelectedItem selectedItem;

  private GameTime gameTime;

  /**
   * Initialize a land with some plants and lands already defined.
   *
   * @param plantModelList the plants to be grown
   * @param gameTime       the current game time
   * @param selectedItem   the selected item used later for checking if the tool can work on the
   *                       tile
   */
  public LandView(List<PlantModel> plantModelList, GameTime gameTime, SelectedItem selectedItem,
      BagItemView bagItemView, TopAnimationView topAnimationView) {
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
        this.piles[i][j] = p;
        this.landGridPane.add(p, i, j);
      }
    }
    for (PlantModel p : plantModelList) {
      PlantView plantView = new PlantView(p, heightSingle, widthSingle);
      this.piles[p.getX()][p.getY()].setPlantView(plantView);
      plantViewList.add(plantView);
    }
    this.bagItemView = bagItemView;
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
  public void check(Pile pile) {
    if (selectedItem.getSelected().equals("img/panda.png")) {
      if (pile.getPlantView() == null) {
        PlantModel plantModel = new PlantModel(gameTime.copy(), new GameTime(0, 0, 1),
            new String[]{"img/half_panda.png", "img/panda.png"}, "img/tool.png", "img/wheat.png",
            pile.getX(),
            pile.getY());
        PlantView p = new PlantView(plantModel, pile.getHeight(), pile.getWidth());
        pile.setPlantView(p);
        plantViewList.add(p);
      }
    } else if (selectedItem.getSelected().equals("img/tool.png")) {
      if (pile.getPlantView() != null &&
          pile.getPlantView().getProgress(gameTime) == 1.0 &&
          pile.getPlantView().getToolUrl().equals(selectedItem.getSelected())) {
        plantViewList.remove(pile.getPlantView());
        BagItem newBagItem = new BagItem(pile.getPlantView().getItemUrl(), PlayingPageView.bottomCellWidth,
            PlayingPageView.bottomCellHeight, 1);

        topAnimationView.collectItemAnimation(newBagItem,
            pile.getCellPosition()[1] - PlayingPageView.windowWidth / 2,
            pile.getCellPosition()[0] - PlayingPageView.windowHeight / 2,
            bagItemView.getAddRealLocation(newBagItem)[1] - PlayingPageView.windowWidth / 2,
            bagItemView.getAddRealLocation(newBagItem)[0] - PlayingPageView.windowHeight / 2, 3.0);
        pile.removePlant();
      }
    }
  }
}
