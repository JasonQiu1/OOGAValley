package oogasalad.view.editor.MapEditor;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class PlantView extends SelectableView {

  private ImageView icon;
  private String title;

  public PlantView(ImageView pic, String title) {
    super(pic, title, 30, 35);
    icon = pic;
    this.title = title;
  }

  @Override
  boolean canBePlacedOn(Node node) {
    return node instanceof TileView;
  }

  @Override
  public SelectableView getNew() {
    return new PlantView(icon, title);
  }
}
