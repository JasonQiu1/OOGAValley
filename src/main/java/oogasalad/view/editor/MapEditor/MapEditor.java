package oogasalad.view.editor.MapEditor;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import oogasalad.model.data.GameConfiguration;


public class MapEditor extends VBox {
  //TODO: make this work

  public MapEditor(GameConfiguration gc) {
    super();
    super.setAlignment(Pos.CENTER);
    Selector ts = new Selector();
    CellInfoPane cip = new CellInfoPane();
    if(gc.getInitialState().getGameWorld() == null){
      System.out.println("what");
    }
    BuildableMap bm = new BuildableMap(ts, cip, gc.getInitialState().getGameWorld());
    TopPanel tp = new TopPanel(bm);
    BuildableMapWrapper bmw = new BuildableMapWrapper(bm);
    BottomPanel bp = new BottomPanel(ts);
    getChildren().addAll(tp, bmw, bp, cip);
    //getChildren().add(new MapExtender(bm));
  }
}
