package oogasalad.view.editor.MapEditor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import oogasalad.model.api.BuildableMapInterface;
import oogasalad.model.api.ReadOnlyGameWorld;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Cell extends StackPane {

  private static final int HEIGHT = 37; //read from file
  private static final int WIDTH = 50;
  private static BuildableMapInterface gameMap;
  private final Rectangle base;
  private int column;
  private int row;
  private int[] id;

  public static void setGameMap(BuildableMapInterface map){
    gameMap = map;
  }

  public Cell(Selector ts, CellInfoPane cip, int i, int j) {
    super();
    setLocalId(i, j);
    column = i;
    row = j;
    base = new Rectangle(WIDTH, HEIGHT);
    base.setFill(Color.WHITE);
    base.setStroke(Color.BLACK);
    base.setStrokeWidth(2);
    super.getChildren().add(base);
    fill();
    setOnMouseClicked(event -> {
      if (event.getButton() == MouseButton.SECONDARY) {
        if (super.getChildren().size() > 1) {
          gameMap.removeTileTop(column, row, 0);
        }
      } else if (ts.getLastSelectedSelectable() != null) {
        gameMap.setTileGameObject(ts.getLastSelectedSelectable(), column, row, 0);
      }
      fill();
      setDisplayPanel(cip);
    });

    setOnMouseEntered(event -> {
      setDisplayPanel(cip);
      base.setFill(Color.GRAY);
      super.getChildren().stream().skip(1) // Skip the first element
          .forEach(node -> node.setOpacity(0.5));
    });

    setOnMouseExited(event -> {
      cip.clearDisplay();
      base.setFill(Color.WHITE);
      super.getChildren().stream().skip(1) // Skip the first element
          .forEach(node -> node.setOpacity(1));
    });


  }

  public static double[] getSize() {
    return new double[]{WIDTH, HEIGHT};
  }

  private void setDisplayPanel(CellInfoPane cip) {
    List<String> content = gameMap.getTileContents(column, row, 0);
    cip.setDisplay(column, row, content);
  }

  private void fill(){
    super.getChildren().removeIf(node -> !node.equals(base));
    if(gameMap != null && !gameMap.getImagePath(column, row, 0).isEmpty()){
      super.getChildren().addAll(getImages());
    }
  }

  private List<ImageView> getImages() {
    List<ImageView> images = new ArrayList<>();
    for(String path: gameMap.getImagePath(column, row, 0)){
      Image image;
      try {
            image = new Image(String.valueOf(new File("data/images/" + path).toURI().toURL()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(WIDTH);
      imageView.setFitHeight(HEIGHT);
      images.add(imageView);

    }
    return images;
  }

  public int getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  public void incrementRow() {
    row++;
    setLocalId(row, column);

  }

  public void incrementColumn() {
    column++;
    setLocalId(row, column);
  }

  public void decrementRow() {
    row--;
    setLocalId(row, column);
  }

  public void decrementColumn() {
    column--;
    setLocalId(row, column);
  }

  private void setLocalId(int i, int j) {
    id = new int[2];
    id[0] = i;
    id[1] = j;
    setLookUpId();
  }

  private void setLookUpId() {
    String lookUpId = String.format("%d_%d", id[0], id[1]);
    this.setId(lookUpId);
  }

  public int[] getLocalId() {
    return id.clone();
  }

  public String getLookUpId() {
    return this.getId();
  }

  public Rectangle getBase() {
    return base;
  }


}
