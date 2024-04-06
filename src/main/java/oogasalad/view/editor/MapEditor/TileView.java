package oogasalad.view.editor.MapEditor;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class TileView extends SelectableView{
    private ImageView icon;
    private String title;
    public TileView(ImageView pic, String title){
        super(pic, title, 50, 37);
        icon = pic;
        this.title = title;
    }

    @Override
    boolean canBePlacedOn(Node node) {
        return node instanceof Rectangle;
    }

    @Override
    public SelectableView getNew() {
        return new TileView(icon, title);
    }
}
