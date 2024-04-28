package oogasalad.view.editor;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.GameObjectEditor.GameObjectEditor;
import oogasalad.view.editor.MapEditor.MapEditor;
import oogasalad.view.editor.RuleEditor.RuleEditor;
import oogasalad.view.editor.RuleEditor.SaveButtonContainer;

public class EditorWindow extends GridPane {

  private final RuleEditor re;
  private final MapEditor me;

  public EditorWindow(Stage stage, Scene backScene, GameConfiguration gc) {
    super();
    //add(GameView, 0,0);
    re = new RuleEditor(gc);
    me = new MapEditor(stage, backScene, gc);
    add(re, 0, 0);
    add(new GameObjectEditor(this::update), 2, 0);
    add(me, 1, 0);
    add(new SaveButtonContainer(new SaveAllButton(gc, re::getName)), 1, 1);
  }

  public void update(){
    me.update();
  }

}
