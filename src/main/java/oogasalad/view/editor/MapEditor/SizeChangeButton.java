package oogasalad.view.editor.MapEditor;

import java.util.function.BiConsumer;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class SizeChangeButton extends Button {

  public SizeChangeButton(BiConsumer<Integer, Integer> action) {
    super("Change \nGrid Size");
    super.setTextAlignment(TextAlignment.CENTER);
    setOnAction(event -> action.accept(null, null)); // Pass null as placeholders for parameters
  }
}
