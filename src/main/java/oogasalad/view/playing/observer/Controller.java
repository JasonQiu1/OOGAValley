package oogasalad.view.playing.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for observer pattern i.e. one class (controller) updates the state and it will
 * tell all its observers
 */
public abstract class Controller<T> {

  private List<Observer<T>> observerList = new ArrayList<>();

  /**
   * add observer to the observer list
   *
   * @param observer
   */
  public void addObserver(Observer<T> observer, T value) {
    observerList.add(observer);
    observer.update(value);
  }

  /**
   * update all the observers
   */
  protected void update(T value) {
    for (Observer<T> observer : observerList) {
      observer.update(value);
    }
  }


}
