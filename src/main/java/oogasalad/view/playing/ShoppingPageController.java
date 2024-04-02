package oogasalad.view.playing;

import java.io.FileNotFoundException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import oogasalad.Game.GameModel.shop.Item;
import oogasalad.Game.GameModel.shop.Shop;
import oogasalad.view.ViewablePng;
import oogasalad.view.exception.FileNotPngException;

/**
 * The view for the shopping page
 */
public class ShoppingPageController {

  @FXML
  private GridPane shoppingItem;

  private int column = 10;

  private double width;

  private Shop shop;

  @FXML
  private Button back;

  private Stage stage;

  private Scene previousScene;

  @FXML
  private void initialize() {
    shop = new Shop();
    width = shoppingItem.getWidth() / 10;
    List<Item> itemList = shop.getItems();
    showItemOnScreen(itemList, 0, shoppingItem);
    back.setOnMouseClicked(event -> {
      stage.setScene(previousScene);
    });
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public void setPreviousScene(Scene scene) {
    this.previousScene = scene;
  }

  private void showItemOnScreen(List<Item> itemList, int idx, GridPane shoppingItem) {
    if (idx == itemList.size()) {
      return;
    }
    Item item = itemList.get(idx);
    try {
      ViewablePng viewablePng = new ViewablePng(item.getUrl(), width, 30);
      Text text = new Text(item.getPrices() + "");
      ImageView imageView = new ImageView(viewablePng.getImage());
      VBox vBox = new VBox();
      vBox.getChildren().add(imageView);
      vBox.getChildren().add(text);
      shoppingItem.add(vBox, idx % column, idx / column);
      showItemOnScreen(itemList, idx + 1, shoppingItem);
    } catch (FileNotPngException e) {
      throw new RuntimeException(e);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }


}
