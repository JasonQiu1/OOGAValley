package oogasalad.view.item;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import oogasalad.view.playing.PlayingPageView;

/**
 * The tool view that controls all the tools (panda and Pickaxe).
 */
public class ToolView {

  private final GridPane toolGridPane;
  private final StackPane toolStackPane;
  private final List<Tool> toolList;
  private final ToolPile[][] toolPiles;

  /**
   * Constructor for the ToolView class.
   *
   * @param tools  the list of tools
   * @param colNum the number of columns
   * @param rowNum the number of rows
   */

  public ToolView(List<Tool> tools, int colNum, int rowNum) {
    this.toolList = tools;
    this.toolGridPane = new GridPane();
    toolPiles = new ToolPile[colNum][rowNum];
    Image backgroundImage = new Image("img/playing/box-background.png");
    ImageView backgroundImageView = new ImageView(backgroundImage);
    backgroundImageView.setFitWidth(PlayingPageView.bottomBoxWidth);
    backgroundImageView.setFitHeight(PlayingPageView.bottomBoxHeight);
    toolStackPane = new StackPane();
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
    // for temp testing
    toolPiles[0][0].getTool().getView().setId("Hoe");
    toolPiles[1][0].getTool().getView().setId("Panda");
    StackPane.setMargin(toolGridPane, new Insets(20, 0, 0, 40));
    toolStackPane.getChildren().addAll(backgroundImageView, toolGridPane);
  }

  public StackPane getToolStackPane() {
    return toolStackPane;
  }


  public void reset() {
    for (Tool tool : toolList) {
      tool.reset();
    }
  }
}
