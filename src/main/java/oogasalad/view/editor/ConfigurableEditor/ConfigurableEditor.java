package oogasalad.view.editor.ConfigurableEditor;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import oogasalad.Game.GameModel.GameConfiguration;
import oogasalad.Game.GameModel.Properties;

import java.util.Map;

public class ConfigurableEditor extends VBox {
    private GameConfiguration gc;
    public ConfigurableEditor(){
        super();
        super.setAlignment(Pos.CENTER);
        gc = new GameConfiguration();
        //Map<String, Properties> configs = gc.getConfigurables();

        //loop over map, determine if the type of object has been seen before
        //(land, structure, item) and if not, 
    }
}
