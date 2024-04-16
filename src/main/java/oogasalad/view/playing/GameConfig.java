package oogasalad.view.playing;

/**
 * The game config class to config the game
 */

public class GameConfig {

  public static final double landCellWidth = 50;
  public static final double landCellHeight = 50;
  public static final double bottomCellWidth = 30;
  public static final double bottomCellHeight = 30;
  public static final int landNumRows = 10;
  public static final int landNumCols = 15;
  public static final double topHeight = 50;
  public static final double topWidth = 800;
  public static final double bottomHeight = 80;
  public static final double bottomWidth = 800;
  public static final double padding = 10;
  public static final double leftRightWidth = 50;
  public static final double landGridPaneWidth = landCellWidth * landNumCols;

  public static final double windowWidth = landGridPaneWidth + leftRightWidth * 2 - padding * 2;
  public static final double landGridPaneHeight = landCellHeight * landNumRows;
  public static final double windowHeight =
      landGridPaneHeight + topHeight + bottomHeight;
  public static final double leftRightHeight = 300;
  public static final double bottomBoxPadding = 50;


}
