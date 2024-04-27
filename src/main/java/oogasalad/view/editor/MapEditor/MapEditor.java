package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import oogasalad.controller.MapController;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;


public class MapEditor extends VBox {
  //TODO: make this work
  private GameConfiguration config;

  public MapEditor(Stage stage, Scene backScene, GameConfiguration gc) {
    super();
    config = gc;
    super.setAlignment(Pos.CENTER);
    Selector ts = new Selector();
    CellInfoPane cip = new CellInfoPane();
    BuildableMap bm = new BuildableMap(ts, cip, new MapController(gc));
    TopPanel tp = new TopPanel(stage, backScene, bm);
    BuildableMapWrapper bmw = new BuildableMapWrapper(bm);
    BottomPanel bp = new BottomPanel(ts, GameConfiguration.getConfigurablesStore().getAllConfigurables());
    getChildren().addAll(tp, bmw, bp, cip);
  }

  public void setConfig(GameConfiguration gc) {
    config = gc;
  }
}
