package oogasalad.view.editor.MapEditor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {
    BuildableMap map;
    Rectangle base;
    final int HEIGHT = 37;
    final int WIDTH = 37;

    public Cell(TileSelector ts) {
        super();
        base = new Rectangle(WIDTH, HEIGHT);
        base.setFill(Color.WHITE);
        base.setStroke(Color.BLACK);
        base.setStrokeWidth(2);
        super.getChildren().add(base);
        setOnMouseClicked(event -> {
            if(ts.getLastTileSelected() != null){
                ImageView formattedImage = this.format(ts.getLastTileSelected().getImage());
                super.getChildren().add(formattedImage);
            }
        });

    }

    private ImageView format(ImageView image) {
        image.setFitHeight(HEIGHT);
        image.setFitWidth(WIDTH);
        return image;
    }
}
