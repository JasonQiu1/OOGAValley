package oogasalad.view.shopping.components.shopblock;

import java.util.List;
import oogasalad.view.shopping.components.PageChangeBorderPane;

/**
 * This class is responsible for creating the page change border pane that is used in the shop block
 * to change pages.
 */
public class SellPageChangeBorderPane extends PageChangeBorderPane<SellGridPane> {

  public SellPageChangeBorderPane(List<SellGridPane> gridPanes) {
    super(gridPanes);
  }
}