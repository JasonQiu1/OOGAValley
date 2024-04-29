package oogasalad.view.editor.MapEditor;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import oogasalad.view.start.ChangePageButton;

public class TopPanel extends StackPane {

  private static final String DEFAULT_RESOURCE_PACKAGE = "view.editor.MapEditor.TopPanel.";
  private final String buttonLanguage = "EnglishButtons";
  private final String titleLanguage = "EnglishTitle";
  private final ResourceBundle buttonResource;
  private final ResourceBundle titleResource;
  private final Stage primaryStage;
  private final Scene previousScene;


  public TopPanel(Stage stage, Scene backScene, BuildableMap bm) {
    super();

    this.primaryStage = stage;
    this.previousScene = backScene;

    titleResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + titleLanguage);
    buttonResource = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + buttonLanguage);

    Label l = new Label(titleResource.getString("map_editor")); //Resource Bundle This
    HBox hbox = new HBox(l);
    hbox.setMinWidth(400);
    hbox.setMaxWidth(400);
    hbox.setAlignment(Pos.CENTER);
    l.getStyleClass().add("map-label");

    SizeChangeButton scb =
        new SizeChangeButton(buttonResource.getString("size_change"), (newI, newJ) -> {
          // Show dialog to get new grid size
          SizeChangeDialogBox dialog = new SizeChangeDialogBox();
          int[] newSize = dialog.getNewSize();
          if (newSize != null) {
            // If user inputs new size, call modifyGridSize method
            bm.modifyGridSizeBL(newSize[1], newSize[0]);
          }
        });

    Button backButton = new Button("Back");
    backButton.setOnAction(event -> goBack());


    HelpButton hb = new HelpButton(buttonResource.getString("help"), e -> {
      // Show dialog to get new grid size
      HelpDialogBox dialog = new HelpDialogBox();
      dialog.show(buttonResource.getString("help"));
    });

    BorderPane bp = new BorderPane();
    bp.setLeft(hb);
    bp.setCenter(backButton);
    bp.setRight(scb);

    BorderPane.setAlignment(hb, Pos.CENTER_LEFT);
    BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
    BorderPane.setAlignment(scb, Pos.CENTER_RIGHT);
    super.getChildren().addAll(hbox, bp);
  }

  private void goBack() {
    primaryStage.setScene(previousScene);
  }

}
