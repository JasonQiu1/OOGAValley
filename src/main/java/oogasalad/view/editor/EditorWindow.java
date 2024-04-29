package oogasalad.view.editor;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import oogasalad.view.editor.GameObjectEditor.GameObjectEditor;
import oogasalad.view.editor.MapEditor.MapEditor;
import oogasalad.view.editor.RuleEditor.RuleEditor;

import java.io.File;

public class EditorWindow extends GridPane {

    private final MapEditor me;
    private final GameObjectEditor goe;

  public EditorWindow(Stage stage, Scene backScene, GameConfiguration gc) {
    super();
    RuleEditor re = new RuleEditor(gc);
    me = new MapEditor(stage, backScene, gc);
    goe = new GameObjectEditor(this::update);
    add(re, 0, 0);
    add(goe, 2, 0);
    add(me, 1, 0);
    add(new BottomButtonContainer(new SaveAllButton(gc, re::getName), new AddPhotoButton(stage, this::savePhoto)), 1, 1);
  }

  public void update(){
    me.update();
  }
  public void savePhoto(File file){
    goe.savePhoto(file);
  }
}
