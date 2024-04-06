package oogasalad.view.editor.MapEditor;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class NatureView extends SelectableView{
    private ImageView icon;
    private String title;
    public NatureView(ImageView pic, String title){
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
        return new NatureView(icon, title);
    }
}
