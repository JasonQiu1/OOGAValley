package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TileView extends VBox {
    private ImageView icon;
    public TileView(ImageView pic, String title){ //resource bundle this
        super();
        super.setAlignment(Pos.CENTER);
        pic.setFitHeight(37);
        pic.setFitWidth(50);
        icon = pic;
        super.getChildren().add(pic);
        super.getChildren().add(new Label(title));
    }

    public ImageView getImage(){
        return new ImageView(icon.getImage());
    }


}
