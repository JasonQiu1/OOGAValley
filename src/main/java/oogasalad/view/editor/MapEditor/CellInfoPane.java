package oogasalad.view.editor.MapEditor;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import oogasalad.view.start.PlayModeSplashScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CellInfoPane extends HBox {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.MapEditor.CellInfoPane.";
  private final String displayTextLanguage = "EnglishDisplayText";
  private final ResourceBundle displayTextResource;
  private static final Logger LOG = LogManager.getLogger(PlayModeSplashScreen.class);
  private final Label displayText;
  private String contentString;
  private int xCor;
  private int yCor;

  public CellInfoPane() {
    super();
    this.setId("CellInfoPane");

    displayTextResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + displayTextLanguage);

    displayText = new Label();
    super.getChildren().add(displayText);
    displayText.setStyle("-fx-font-size: 25");
    clearDisplay();
    super.setMinHeight(displayText.getHeight());
  }

  public void setDisplay(int xpos, int ypos, List<String> content) {
    xCor = xpos;
    yCor = ypos;
    contentString = buildContentString(content);
    displayText.setText(displayTextResource.getString("position") + " " + xCor + "," + yCor + "\n"
        + displayTextResource.getString("cell") + " " + contentString);
  }

  private String buildContentString(List<String> content) {
    return String.join(", ", content);
  }

  public void clearDisplay() {
    displayText.setText(
        displayTextResource.getString("position") + " \n" + displayTextResource.getString("cell")
            + " ");
  }

  public int getxCor() {
    return xCor;
  }

  public int getyCor() {
    return yCor;
  }

  public String getContentString() {
    return contentString;
  }
}
