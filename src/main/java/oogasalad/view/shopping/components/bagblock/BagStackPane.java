package oogasalad.view.shopping.components.bagblock;

import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.model.api.GameInterface;
import oogasalad.view.shopping.components.ItemStackPane;
import oogasalad.view.shopping.components.ItemView;
import oogasalad.view.shopping.components.PageChangeBorderPane;

/**
 * This class is responsible for creating the bag stack pane that is used to display the items in
 * the bag.
 */
public class BagStackPane extends ItemStackPane<BagGridPane> {

  public BagStackPane(GameInterface game, StackPane parentStackPane) {
    super(game, parentStackPane);
  }

  @Override
  protected String getBackgroundImagePath() {
    return "img/shop/bag-background.png";
  }


  @Override
  protected BagGridPane createGridPane(GameInterface game, List<ItemView> sublist,
      StackPane parentStackPane) {
    return new BagGridPane(game, sublist, parentStackPane);
  }

  @Override
  protected PageChangeBorderPane createPageChangeBorderPane(
      List<? extends GridPane> gridPanes) {
    return new BagPageChangeBorderPane((List<BagGridPane>) gridPanes);
  }

  @Override
  protected double getLeftMargin() {
    return 0;
  }

  @Override
  protected double getTopMargin() {
    return 0;
  }
}

