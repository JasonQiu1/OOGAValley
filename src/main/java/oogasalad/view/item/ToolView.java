package oogasalad.view.item;

import java.util.ArrayList;
import java.util.List;

/**
 * The tool view that controls all the tools (panda and Pickaxe)
 */
public class ToolView {

  private List<Tool> toolList;

  public ToolView() {
    this.toolList = new ArrayList<>();
  }

  public void add(Tool tool) {
    toolList.add(tool);
  }

  public void reset() {
    for (Tool tool : toolList) {
      tool.reset();
    }
  }
}
