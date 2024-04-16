package oogasalad.view.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for observer pattern i.e. one class (controller) updates the state and it will
 * tell all its observers
 */
public abstract class Controller<T> {

  private List<Observer> observerList = new ArrayList<>();

  /**
   * add observer to the observer list
   *
   * @param observer
   */
  public void addObserver(Observer observer) {
    observerList.add(observer);
  }

  /**
   * update all the observers
   */
  protected void update(T value) {
    for (Observer observer : observerList) {
      observer.update(value);
    }
  }

}
