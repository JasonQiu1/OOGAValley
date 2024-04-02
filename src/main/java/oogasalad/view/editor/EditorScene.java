package oogasalad.view.editor;

import javafx.scene.Scene;

public class EditorScene extends Scene {
    public EditorScene(){
        super(new EditorWindow());
        super.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

    }
}
