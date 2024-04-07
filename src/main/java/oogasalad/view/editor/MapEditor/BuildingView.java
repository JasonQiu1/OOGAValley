package oogasalad.view.editor.MapEditor;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class BuildingView extends SelectableView {

  private ImageView icon;
  private String title;

  public BuildingView(ImageView pic, String title) {
    super(pic, title, 40, 35);
    icon = pic;
    this.title = title;
  }

  @Override
  boolean canBePlacedOn(Node node) {
    return node instanceof TileView;
  }

  @Override
  public SelectableView getNew() {
    return new BuildingView(icon, title);
  }
}
