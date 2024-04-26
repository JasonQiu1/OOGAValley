package oogasalad.view.editor;

import javafx.scene.layout.GridPane;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.MapEditor.MapEditor;
import oogasalad.view.editor.RuleEditor.RuleEditor;

public class EditorWindow extends GridPane {

  private RuleEditor re;
  private MapEditor me;
  public EditorWindow(GameConfiguration gc) {
    super();
    //add(GameView, 0,0);
    me = new MapEditor(gc);
    re = new RuleEditor(gc);
    add(re, 0, 0);
    add(me, 1, 0);
    //add(ItemEditor, 1, 0);
  }

  public void setConfig(GameConfiguration gc) {
    re.setConfig(gc);
    me.setConfig(gc);
  }
}
