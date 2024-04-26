package oogasalad.view.editor;

import javafx.scene.layout.GridPane;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.MapEditor.MapEditor;
import oogasalad.view.editor.RuleEditor.RuleEditor;

public class EditorWindow extends GridPane {

  private RuleEditor re;
  public EditorWindow(GameConfiguration gc) {
    super();
    //add(GameView, 0,0);
    add(new MapEditor(), 1, 0);
    re = new RuleEditor(gc);
    add(re, 0, 0);
    //add(ItemEditor, 1, 0);
  }

  public void setConfig(GameConfiguration gc) {
    re.setConfig(gc);
  }
}
