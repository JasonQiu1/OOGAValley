package oogasalad.view.shopping.components.bagblock;

import java.util.List;
import oogasalad.view.shopping.components.PageChangeBorderPane;
import oogasalad.view.shopping.components.bagblock.BagGridPane;
import oogasalad.view.shopping.components.shopblock.SellGridPane;

public class BagPageChangeBorderPane extends PageChangeBorderPane<BagGridPane> {
  public BagPageChangeBorderPane(List<BagGridPane> gridPanes) {
    super(gridPanes);
  }
}