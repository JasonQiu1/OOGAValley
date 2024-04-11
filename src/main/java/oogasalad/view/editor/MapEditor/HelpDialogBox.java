package oogasalad.view.editor.MapEditor;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class HelpDialogBox {

    public void show() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setContent(getHelp());
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.showAndWait();

    }

    private Node getHelp() {
        Label help = new Label();
        String helpText = "This is the Map Editor! You can use it to edit the \n" +
                          "game map. \n\n\n" +
                          "-You can use the \"Change Grid Size\" button \n" +
                          "to set the grid to a specific size \n\n" +
                          "-To add or remove a row or column from a specific \n" +
                          "side, click the box that appears when your mouse \n" +
                          "hovers over edges of the grid \n(Click Green to Grow and Red to Shrink)\n\n" +
                          "-Select Tiles from the Selector at the bottom of the \n" +
                          "Map Editor and then click a grid cell to place the tile \n\n" +
                          "-Once a tile is placed, you can place nature, plants, \n" +
                          "and buildings on the tile\n\n" +
                          "-To remove tiles, nature, plants, and buildings from a \n" +
                          "cell, right click cell \n\n" +
                          "-Running your mouse over a cell will display that cell's \n" +
                          "location and any selectables placed in the cell \n\n" +
                          "Have Fun!";
        help.setText(helpText);
        return help;
    }
}