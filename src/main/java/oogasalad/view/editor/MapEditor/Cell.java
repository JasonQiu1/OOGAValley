package oogasalad.view.editor.MapEditor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {
    private ImageView image;
    private final Rectangle base;
    private final int HEIGHT = 37; //read from file
    private final int WIDTH = 50;
    private final int i;
    private final int j;

    public Cell(TileSelector ts, int i, int j) {
        super();
        this.i = i;
        this.j = j;
        base = new Rectangle(WIDTH, HEIGHT);
        base.setFill(Color.WHITE);
        base.setStroke(Color.BLACK);
        base.setStrokeWidth(2);
        super.getChildren().add(base);
        setOnMouseClicked(event -> {
            if(ts.getLastTileSelected() != null){
                super.getChildren().remove(image);
                if(event.getButton() == MouseButton.SECONDARY){
                    image = null;
                }
                else{
                    ImageView formattedImage = this.format(ts.getLastTileSelected().getImage());
                    image = formattedImage;
                    super.getChildren().add(formattedImage);
                }
            }
        });

        setOnMouseEntered(event -> {
            base.setFill(Color.GRAY);
            if(image != null){
                image.setOpacity(.5);
            }
        });
        setOnMouseExited(event -> {
            base.setFill(Color.WHITE);
            if(image != null){
                image.setOpacity(1);
            }
        });


    }

    private ImageView format(ImageView image) {
        image.setFitHeight(HEIGHT);
        image.setFitWidth(WIDTH);
        return image;
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }
}
