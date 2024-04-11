package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import oogasalad.view.start.ChangePageButton;
import oogasalad.view.start.PlayModeSplashScreen;
import oogasalad.view.start.StartScreen;

public class TopPanel extends StackPane {

  public TopPanel(BuildableMap bm) {
    super();
    Label l = new Label("Map Editor"); //Resource Bundle This
    HBox hbox = new HBox(l);
    hbox.setMinWidth(650);
    hbox.setAlignment(Pos.CENTER);
    l.getStyleClass().add("map-label");
//    SizeChangeButtonWrapper scbw = new SizeChangeButtonWrapper(
//        new SizeChangeButton((newI, newJ) -> {
//          // Show dialog to get new grid size
//          SizeChangeDialogBox dialog = new SizeChangeDialogBox();
//          int[] newSize = dialog.getNewSize();
//          if (newSize != null) {
//            // If user inputs new size, call modifyGridSize method
//            bm.modifyGridSizeBL(newSize[1], newSize[0]);
//          }
//        }));

      SizeChangeButton scb = new SizeChangeButton((newI, newJ) -> {
          // Show dialog to get new grid size
          SizeChangeDialogBox dialog = new SizeChangeDialogBox();
          int[] newSize = dialog.getNewSize();
          if (newSize != null) {
              // If user inputs new size, call modifyGridSize method
              bm.modifyGridSizeBL(newSize[1], newSize[0]);
          }
      });

//      HelpButtonWrapper hbw = new HelpButtonWrapper(
//              new HelpButton(e -> {
//                  // Show dialog to get new grid size
//                  HelpDialogBox dialog = new HelpDialogBox();
//                  dialog.show();
//              }));

      HelpButton hb = new HelpButton(e -> {
          // Show dialog to get new grid size
          HelpDialogBox dialog = new HelpDialogBox();
          dialog.show();
      });

//      GridPane gp = new GridPane();
//      gp.add(hb, 0, 0);
//      gp.add(scb, 1, 0);
      BorderPane bp = new BorderPane();
      bp.setLeft(hb);
      bp.setRight(scb);

      BorderPane.setAlignment(hb, Pos.CENTER_LEFT);
      BorderPane.setAlignment(scb, Pos.CENTER_RIGHT);
    super.getChildren().addAll(hbox, bp);
  }

}
