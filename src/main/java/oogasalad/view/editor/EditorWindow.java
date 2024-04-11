package oogasalad.view.editor;

import javafx.scene.layout.GridPane;
import oogasalad.view.editor.ConfigurableEditor.ConfigurableEditor;
import oogasalad.view.editor.MapEditor.MapEditor;

public class EditorWindow extends GridPane {

  public EditorWindow() {
    super();
    //add(GameView, 0,0);
    add(new MapEditor(), 1, 0);
    //add(RuleEditor, 0, 1);
    add(new ConfigurableEditor(), 1, 1);
  }
}
