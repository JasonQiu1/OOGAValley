package oogasalad.view.editor.MapEditor;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SelectableViews extends HBox {

  public SelectableViews(TileSelector ts) {
    super();
    super.setSpacing(8);
    try {
      mockTiles(ts);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private void mockTiles(TileSelector ts) throws MalformedURLException {
    SelectableView t1 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/dirt.jpg").toURI().toURL()))), "Dirt");
    ts.addTile(t1);
    super.getChildren().add(t1);

    SelectableView t2 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/grass.jpg").toURI().toURL()))), "Grass");
    ts.addTile(t2);
    super.getChildren().add(t2);

    SelectableView t3 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/lava.jpg").toURI().toURL()))), "Lava");
    ts.addTile(t3);
    super.getChildren().add(t3);

    SelectableView t4 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/sand.jpg").toURI().toURL()))), "Sand");
    ts.addTile(t4);
    super.getChildren().add(t4);

    SelectableView t5 = new TileView(new ImageView(new Image(
        String.valueOf(new File("src/main/resources/img/water.jpg").toURI().toURL()))), "Water");
    ts.addTile(t5);
    super.getChildren().add(t5);
  }

  //TODO: get tiles from model

}
