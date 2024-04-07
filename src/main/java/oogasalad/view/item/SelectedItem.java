package oogasalad.view.item;

import java.util.Map;
import oogasalad.Game.GameModel.gameplay.PropertyReader;

/**
 * The selected item denoting the selected item
 */
public class SelectedItem {

  private String selected = "img/plant.png";

  public void setSelected(String selected) {
    this.selected = selected;
  }

  public String getSelected() {
    return selected;
  }

}
