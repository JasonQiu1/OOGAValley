package oogasalad.view.item;

import java.util.List;
import javafx.scene.layout.GridPane;
import oogasalad.view.playing.PlayingPageView;

/**
 * The tool view that controls all the tools (panda and Pickaxe).
 */
public class ToolView {

  private final GridPane toolGridPane;
  private List<Tool> toolList;
  private ToolPile[][] toolPiles;
  /**
   * Constructor for the ToolView class.
   *
   * @param tools the list of tools
   * @param colNum the number of columns
   * @param rowNum the number of rows
   */

  public ToolView(List<Tool> tools, int colNum, int rowNum) {
    this.toolList = tools;
    this.toolGridPane = new GridPane();
    toolPiles = new ToolPile[colNum][rowNum];
    for (int i = 0; i < colNum; i++) {
      for (int j = 0; j < rowNum; j++) {
        ToolPile p = new ToolPile(null, i, j);
        p.setPrefHeight(PlayingPageView.bottomCellHeight);
        p.setPrefWidth(PlayingPageView.bottomCellWidth);
        toolPiles[i][j] = p;
        toolGridPane.add(p, i, j);
      }
    }
    for (int i = 0; i < tools.size(); i++) {
      toolPiles[i][0].setTool(tools.get(i));
      int finalI = i;
      toolPiles[i][0].getTool().getView().setOnMouseClicked(event -> {
        reset();
        toolPiles[finalI][0].getTool().setSelected();
      });
    }

  }

  public GridPane getToolGridPane() {
    return toolGridPane;
  }


  public void reset() {
    for (Tool tool : toolList) {
      tool.reset();
    }
  }
}
