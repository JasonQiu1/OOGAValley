package oogasalad.model.shop;

import java.util.ArrayList;
import java.util.List;
import oogasalad.view.item.Money;

/**
 * The class on behalf of the shop
 */
public class Shop {

  private final List<SellItem> sellItems;
  private final Money moneyModel = new Money(100);
  private final double currentEnergy = 1;

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
    return moneyModel.getMoney();
  }

  public Money getMoneyModel() {
    return moneyModel;
  }

  public void addMoney(int money) {
    moneyModel.setMoney(moneyModel.getMoney() + money);

  }

  public double getCurrentEnergy() {
    return currentEnergy;
  }
}
