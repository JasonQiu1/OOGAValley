package oogasalad.view.playing.component;

import javafx.scene.layout.GridPane;
import oogasalad.model.api.ReadOnlyGameWorld;

/**
 * A 2d grid representing the land and the plants (buildings).
 */
public class LandView {


  private final GridPane landGridPane = new GridPane();

  //  private TopAnimationView topAnimationView;
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

//  /**
//   * Set the onclick method for a chosen item.
//   *
//   * @param pile the pile to be updated
//   */
//  public void check(Pile pile, MouseEvent event) {
//    if (selectedItem.getSelected().equals("img/panda.png")) {
//      if (pile.getPlantView() == null) {
//        PlantModel plantModel = new PlantModel.Builder().setPlantedTime(gameTime.copy())
//            .setMatureTime(new GameTime(0, 0, 1))
//            .setStatusImagePath(new String[]{"img/half_panda.png", "img/panda.png"})
//            .setItemUrl("img/wheat.png").setToolUrl("img/tool.png")
//            .setX(pile.getX()).setY(pile.getY()).build();
//        PlantView p = new PlantView(plantModel, pile.getHeight(), pile.getWidth());
//        pile.setPlantView(p);
//        plantViewList.add(p);
//      }
//    } else if (selectedItem.getSelected().equals("img/tool.png")) {
//      if (pile.getPlantView() != null &&
//          pile.getPlantView().getProgress(gameTime) == 1.0 &&
//          pile.getPlantView().getToolUrl().equals(selectedItem.getSelected())) {
//        plantViewList.remove(pile.getPlantView());
//        BagItem newBagItem = new BagItem(pile.getPlantView().getItemUrl(),
//            PlayingPageView.bottomCellWidth,
//            PlayingPageView.bottomCellHeight, selectedItem, 1);
//        topAnimationView.collectItemAnimation(newBagItem,
//            event.getSceneX() - PlayingPageView.windowWidth / 2,
//            event.getSceneY() - PlayingPageView.windowHeight / 2,
//            bagView.getAddRealLocation(newBagItem)[1],
//            bagView.getAddRealLocation(newBagItem)[0], 3.0);
//
//        pile.removePlant();
//      }
//    }
//  }
}
