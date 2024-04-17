package oogasalad.view.popup.buttons;

import javafx.scene.control.Button;

/**
 * This class is a Button that represents the YesButton in a pop-up window.
 */
public class YesButton extends Button {

  private final String text;

  /**
   * Constructor for YesButton
   *
   * @param text the text to be displayed on the YesButton
   */
  public YesButton(String text) {
    super();
    this.text = text;
    initialize();
  }

  private void initialize() {
    setId("yes-button");
    setText(text);
  }

}
