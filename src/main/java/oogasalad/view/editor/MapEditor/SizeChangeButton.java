package oogasalad.view.editor.MapEditor;

import java.util.function.BiConsumer;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class SizeChangeButton extends Button {

  public SizeChangeButton(String name, BiConsumer<Integer, Integer> action) {
//    super("Change \nGrid Size");
    super(name);
    setId("SizeChangeButton");
    super.setTextAlignment(TextAlignment.CENTER);
    super.setPrefWidth(125);
    //super.setWrapText(true);
    setOnAction(event -> action.accept(null, null)); // Pass null as placeholders for parameters
  }
}
