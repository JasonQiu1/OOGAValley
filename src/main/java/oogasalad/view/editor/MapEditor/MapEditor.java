package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import oogasalad.model.data.GameConfiguration;


public class MapEditor extends VBox {
  //TODO: make this work
  private GameConfiguration config;

  public MapEditor(GameConfiguration gc) {
    super();
    config = gc;
    super.setAlignment(Pos.CENTER);
    Selector ts = new Selector();
    CellInfoPane cip = new CellInfoPane();
//    // BuildableMap bm = new BuildableMap(ts, cip, config.getInitialState().getGameWorld());
////    TopPanel tp = new TopPanel(bm);
////    BuildableMapWrapper bmw = new BuildableMapWrapper(bm);
//    BottomPanel bp = new BottomPanel(ts, GameConfiguration.getConfigurablesStore().getAllConfigurables());
//    getChildren().addAll(tp, bmw, bp, cip);
//    //getChildren().add(new MapExtender(bm));
  }

  public void setConfig(GameConfiguration gc) {
    config = gc;
  }
}
