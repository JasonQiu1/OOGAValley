package oogasalad.view.editor.MapEditor;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {
    private ImageView image;
    private final Rectangle base;
    private static final int HEIGHT = 37; //read from file
    private static final int WIDTH = 50;
    private int column;
    private int row;

    public Cell(TileSelector ts, int i, int j) {
        super();
        column = i;
        row = j;
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
      if (image != null) {
        image.setOpacity(.5);
      }
    });
    setOnMouseExited(event -> {
      base.setFill(Color.WHITE);
      if (image != null) {
        image.setOpacity(1);
      }
    });


  }

  private ImageView format(ImageView image) {
    image.setFitHeight(HEIGHT);
    image.setFitWidth(WIDTH);
    return image;
  }

    public int getColumn(){
        return column;
    }

    public int getRow(){
        return row;
    }

    public void incrementRow(){
        row++;
    }

    public void incrementColumn(){
        column++;
    }

    public void decrementRow() {
        row--;
    }

    public void decrementColumn(){
        column--;
    }

    public static double[] getSize() {
        return new double[]{WIDTH, HEIGHT};
    }

}
