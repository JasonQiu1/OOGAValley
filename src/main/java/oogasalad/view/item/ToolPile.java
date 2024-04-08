package oogasalad.view.item;

import javafx.scene.layout.StackPane;

/**
 * Represents a pile of tools in the game
 */
public class ToolPile extends StackPane {

  private final int x;
  private final int y;
  private Tool tool;

  public ToolPile(Tool tool, int x, int y) {
    this.tool = tool;
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Tool getTool() {
    return tool;
  }

  public void setTool(Tool tool) {
    this.tool = tool;
    this.getChildren().add(0, tool.getView());
  }
}
