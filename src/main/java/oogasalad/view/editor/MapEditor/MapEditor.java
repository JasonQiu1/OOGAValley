package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import oogasalad.controller.MapController;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;


public class MapEditor extends VBox {
  //TODO: make this work
  private BottomPanel bp;
  private Stage stage;
  private Scene backScene;
  private GameConfiguration gc;

  public MapEditor(Stage stage, Scene backScene, GameConfiguration gc) {
    super();
    super.setAlignment(Pos.TOP_CENTER);
    this.stage = stage;
    this.backScene = backScene;
    this.gc = gc;
    update();
  }

  public void update(){
    getChildren().clear();
    CellInfoPane cip = new CellInfoPane();
    BuildableMap bm = new BuildableMap(cip, new MapController(gc));
    TopPanel tp = new TopPanel(stage, backScene, bm);
    BuildableMapWrapper bmw = new BuildableMapWrapper(bm);
    bp = new BottomPanel(GameConfiguration.getConfigurablesStore().getAllConfigurables());
    getChildren().addAll(tp, bmw, bp, cip);
  }
}
