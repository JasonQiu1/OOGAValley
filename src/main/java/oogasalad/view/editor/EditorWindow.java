package oogasalad.view.editor;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.GameObjectEditor.GameObjectEditor;
import oogasalad.view.editor.MapEditor.MapEditor;
import oogasalad.view.editor.RuleEditor.RuleEditor;

public class EditorWindow extends GridPane {

  private final RuleEditor re;

  public EditorWindow(Stage stage, Scene backScene, GameConfiguration gc) {
    super();
    //add(GameView, 0,0);
    re = new RuleEditor(gc);
    add(re, 0, 0);
    add(new GameObjectEditor(), 2, 0);
    add(new MapEditor(stage, backScene, gc), 1, 0);
    //add(ItemEditor, 1, 0);
  }

  public void setConfig(GameConfiguration gc) {
    re.setConfig(gc);
  }
}
