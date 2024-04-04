package oogasalad.view.editor.MapEditor;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TopPanel extends StackPane {

    public TopPanel(BuildableMap bm){
        Label l = new Label("Map Editor"); //Resource Bundle This
        l.getStyleClass().add("map-label");
        SizeChangeButtonWrapper scbw = new SizeChangeButtonWrapper(new SizeChangeButton((newI, newJ) -> {
            // Show dialog to get new grid size
            DialogBox dialog = new DialogBox();
            int[] newSize = dialog.getNewSize();
            if (newSize != null) {
                // If user inputs new size, call modifyGridSize method
                bm.modifyGridSize(newSize[1], newSize[0]);
            }
        }));
        super.getChildren().addAll(l, scbw);
    }
}
