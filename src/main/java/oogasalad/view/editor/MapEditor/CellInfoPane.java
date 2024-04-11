package oogasalad.view.editor.MapEditor;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;
import oogasalad.view.start.PlayModeSplashScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CellInfoPane extends HBox {
    private static final Logger LOG = LogManager.getLogger(PlayModeSplashScreen.class);
    private final Label displayText;
    private String svsToStringCopy;
    private int xCor;
    private int yCor;
    public CellInfoPane(){
        super();
        this.setId("CellInfoPane");
        displayText = new Label();
        super.getChildren().add(displayText);
        displayText.setStyle("-fx-font-size: 25");
        clearDisplay();
        super.setMinHeight(displayText.getHeight());
    }

    public void setDisplay(int xpos, int ypos, ObservableList<Node> svs){
        xCor = xpos;
        yCor = ypos;
        svsToStringCopy = svsToString(svs);

        displayText.setText("Position (x,y): " +xCor + "," + yCor + "\n" +
                              "In Cell: " + svsToStringCopy);
    }

    private String svsToString(ObservableList<Node> svs) {
        StringBuilder listOfContent = new StringBuilder();
        for(Node sv : svs){
            listOfContent.append(((SelectableView)sv).getLabel().getText()).append(", ");
        }
        if(!svs.isEmpty()){
            listOfContent.delete(listOfContent.length() - 2, listOfContent.length() -1);
        }
        return listOfContent.toString().trim();
    }

    public void clearDisplay() {
        displayText.setText("Position (x,y): \n" +
                "In Cell: ");
    }

    public int getxCor() {
        return xCor;
    }

    public int getyCor() {
        return yCor;
    }

    public String getSvsToStringCopy() {
        return svsToStringCopy;
    }
}
