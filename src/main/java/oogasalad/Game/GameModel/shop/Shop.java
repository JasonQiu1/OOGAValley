package oogasalad.Game.GameModel.shop;

import java.util.ArrayList;
import java.util.List;

/**
 * The class on behalf of the shop
 */
public class Shop {

  private final List<Item> items;

  public Shop(List<Item> items) {
    this.items = items;
  }

  //TODO: change the default initialization of the Shop object
  public Shop() {
    items = new ArrayList<>();
    items.add(new Item(500, "src/main/resources/img/tool.png"));
    items.add(new Item(200, "src/main/resources/img/rock.png"));
    items.add(new Item(100, "src/main/resources/img/panda.png"));
  }

  public List<Item> getItems() {
    return items;
  }

}
