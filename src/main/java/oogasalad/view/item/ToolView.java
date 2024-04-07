package oogasalad.view.item;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * The tool view that controls all the tools (panda and Pickaxe)
 */
public class ToolView {
  private final GridPane toolGridPane;
  private List<Tool> toolList;
  private int numTools;

  public ToolView() {
    numTools = 0;
    this.toolList = new ArrayList<>();
    this.toolGridPane = new GridPane();

  }

  public GridPane getToolGridPane() {
    return toolGridPane;
  }
  public void add(Tool tool) {
    toolList.add(tool);
    toolGridPane.add(toolList.get(numTools).getImageView(), numTools, 0);
    numTools++;
  }

  public void reset() {
    for (Tool tool : toolList) {
      tool.reset();
    }
  }
}
