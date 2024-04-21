package oogasalad.view.playing.observer;

/**
 * The observer class to recive the update from the controller class
 *
 * @param <T>
 */
public interface Observer<T> {

  void update(T value);

}
