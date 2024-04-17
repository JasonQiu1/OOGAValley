package oogasalad.view.editor.MapEditor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class HelpDialogBox {
  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.MapEditor.HelpDialogBox.";
  private String helpTextPath = "EnglishHelpFilePath";
  private ResourceBundle helpTextPathResource;

  public void show(String title) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    helpTextPathResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+ helpTextPath);

    alert.getDialogPane().setContent(getHelp(helpTextPathResource.getString("file_path")));
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.showAndWait();

  }

  private Node getHelp(String textPath) {
    Label help = new Label();
//    String helpText = "This is the Map Editor! You can use it to edit the \n" +
//        "game map. \n\n\n" +
//        "-You can use the \"Change Grid Size\" button \n" +
//        "to set the grid to a specific size \n\n" +
//        "-To add or remove a row or column from a specific \n" +
//        "side, click the box that appears when your mouse \n" +
//        "hovers over edges of the grid \n(Click Green to Grow and Red to Shrink)\n\n" +
//        "-Select Tiles from the Selector at the bottom of the \n" +
//        "Map Editor and then click a grid cell to place the tile \n\n" +
//        "-Once a tile is placed, you can place nature, plants, \n" +
//        "and buildings on the tile\n\n" +
//        "-To remove tiles, nature, plants, and buildings from a \n" +
//        "cell, right click cell \n\n" +
//        "-Running your mouse over a cell will display that cell's \n" +
//        "location and any selectables placed in the cell \n\n" +
//        "Have Fun!";

      // Read all lines from the file into a List of Strings
    List<String> lines = null;
    try {
      lines = Files.readAllLines(Paths.get(textPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // Join the lines using newline character (\n) to form a single string
      String helpText = String.join("\n", lines);


    help.setText(helpText);
    return help;
  }
}