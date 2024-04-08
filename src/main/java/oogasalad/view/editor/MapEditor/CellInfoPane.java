package oogasalad.view.editor.MapEditor;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;

public class CellInfoPane extends HBox {
    private final Label displayText;
    public CellInfoPane(){
        super();
        displayText = new Label();
        super.getChildren().add(displayText);
        displayText.setStyle("-fx-font-size: 25");
        clearDisplay();
        super.setMinHeight(displayText.getHeight());
    }

    public void setDisplay(int xpos, int ypos, ObservableList<Node> svs){
        String svsToString = svsToString(svs);
        displayText.setText("Position (x,y): " +xpos + "," + ypos + "\n" +
                              "In Cell: " + svsToString);
    }

    private String svsToString(ObservableList<Node> svs) {
        StringBuilder listOfContent = new StringBuilder();
        for(Node sv : svs){
            listOfContent.append(((SelectableView)sv).getLabel().getText()).append(", ");
        }
        if(!svs.isEmpty()){
            listOfContent.delete(listOfContent.length() - 2, listOfContent.length() -1);
        }
        return listOfContent.toString();
    }

    public void clearDisplay() {
        displayText.setText("Position (x,y): \n" +
                "In Cell: ");
    }
}
