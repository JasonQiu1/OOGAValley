package oogasalad.view.shopping;

import java.io.FileNotFoundException;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.Game.GameModel.shop.SellItem;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.ViewablePng;
import oogasalad.view.branch.BranchBase;
import oogasalad.view.exception.FileNotPngException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The view for the shopping page
 */
public class ShoppingPageView extends BranchBase {


  private static final Logger LOG = LogManager.getLogger(ShoppingPageView.class);
  private final int column = 10;
  private final GridPane shoppingItem;
  private final double width;
  private final HBox hBox = new HBox();
  private final BorderPane borderPane = new BorderPane();
  private final Button back = new Button("back");

  public ShoppingPageView(Stage stage, Scene previousScene) {
    super(stage, previousScene);
    Shop shop = new Shop();
    shoppingItem = new GridPane();
    shoppingItem.setPrefWidth(800.0);
    shoppingItem.setPrefHeight(500.0);
    width = shoppingItem.getWidth() / 10;
    borderPane.setTop(new Text("Shop"));
    borderPane.setCenter(shoppingItem);
    borderPane.setBottom(back);
    List<SellItem> sellItemList = shop.getItems();
    showItemOnScreen(sellItemList, 0, shoppingItem);
    back.setOnMouseClicked(event -> {
      getStage().setScene(getPreviousScene());
    });
  }

  public Parent getScene() {
    return borderPane;
  }

  private void showItemOnScreen(List<SellItem> sellItemList, int idx, GridPane shoppingItem) {
    if (idx == sellItemList.size()) {
      return;
    }
    SellItem sellItem = sellItemList.get(idx);
    try {
      ViewablePng viewablePng = new ViewablePng(sellItem.getUrl(), width, 30);
      Text text = new Text(sellItem.getPrices() + "");
      ImageView imageView = new ImageView(viewablePng.getImage());
      VBox vBox = new VBox();
      vBox.getChildren().add(imageView);
      vBox.getChildren().add(text);
      vBox.setOnMouseClicked(event -> {
        LOG.info(sellItem.getPrices());
      });
      shoppingItem.add(vBox, idx % column, idx / column);
      showItemOnScreen(sellItemList, idx + 1, shoppingItem);
    } catch (FileNotPngException | FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }


}
