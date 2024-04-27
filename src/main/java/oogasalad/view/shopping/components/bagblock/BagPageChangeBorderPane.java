package oogasalad.view.shopping.components.bagblock;

import java.util.List;
import oogasalad.view.shopping.components.PageChangeBorderPane;

/**
 * This class is responsible for creating the bag page change border pane that is used to change
 * pages.
 */
public class BagPageChangeBorderPane extends PageChangeBorderPane<BagGridPane> {

  public BagPageChangeBorderPane(List<BagGridPane> gridPanes) {
    super(gridPanes);
  }
}