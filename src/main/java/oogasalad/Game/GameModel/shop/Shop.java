package oogasalad.Game.GameModel.shop;

import java.util.ArrayList;
import java.util.List;

/**
 * The class on behalf of the shop
 */
public class Shop {

  private final List<SellItem> sellItems;
  private double currentMoney = 270;
  private double currentEnergy = 1;


  public Shop(List<SellItem> sellItems) {
    this.sellItems = sellItems;
  }

  //TODO: change the default initialization of the Shop object
  public Shop() {
    sellItems = new ArrayList<>();
    sellItems.add(new SellItem(500, "img/tool.png"));
    sellItems.add(new SellItem(200, "img/rock.png"));
    sellItems.add(new SellItem(100, "img/panda.png"));
    sellItems.add(new SellItem(500, "img/tool.png"));
    sellItems.add(new SellItem(200, "img/rock.png"));
    sellItems.add(new SellItem(100, "img/panda.png"));
  }

  public List<SellItem> getItems() {
    return sellItems;
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
