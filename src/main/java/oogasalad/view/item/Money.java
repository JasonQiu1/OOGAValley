package oogasalad.view.item;

import oogasalad.view.item.observer.Controller;

/**
 * Represents the global money
 */
public class Money extends Controller<Integer> {

  private int money;

  public Money(int money) {
    super();
    this.money = money;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
    update(money);
  }
}
