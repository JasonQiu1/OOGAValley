package oogasalad.view.popup.buttons;

import javafx.scene.control.Button;

/**
 * This class is a Button that represents the NoButton in a pop-up window.
 */
public class NoButton extends Button {

  private final String text;

  /**
   * Constructor for NoButton
   *
   * @param text the text to be displayed on the NoButton
   */
  public NoButton(String text) {
    super();
    this.text = text;
    initialize();
  }

  private void initialize() {
    setId("no-button");
    setText(text);
  }
}
