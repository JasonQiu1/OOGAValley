package oogasalad.Game.GameModel.shop;

import java.util.ArrayList;
import java.util.List;

/**
 * The class on behalf of the shop
 */
public class Shop {

  private final List<Item> items;
  private double currentMoney = 270;
  private double currentEnergy = 1;


  public Shop(List<Item> items) {
    this.items = items;
  }

  //TODO: change the default initialization of the Shop object
  public Shop() {
    items = new ArrayList<>();
    items.add(new Item(500, "img/tool.png"));
    items.add(new Item(200, "img/rock.png"));
    items.add(new Item(100, "img/panda.png"));
  }

  public List<Item> getItems() {
    return items;
  }

  public double getCurrentMoney() {
    return currentMoney;
  }
  public void addMoney(double money) {
    currentMoney += money;
  }
  public double getCurrentEnergy() {
    return currentEnergy;
  }
}
