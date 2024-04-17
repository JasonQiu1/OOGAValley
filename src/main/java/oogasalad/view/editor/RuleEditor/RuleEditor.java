package oogasalad.view.editor.RuleEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import oogasalad.model.data.GameConfiguration;

public class RuleEditor extends BorderPane {
    public RuleEditor(GameConfiguration gc){
        super();
        Label l = new Label("Rule Editor");
        l.getStyleClass().add("editor-label");
        RuleDisplay rd = new RuleDisplay(gc.getRules().getCopyOfProperties());
        VBox vbox = new VBox();
        vbox.getChildren().addAll(l, rd);
        super.setTop(vbox);
        HBox bottom = new HBox(new SaveButton(e -> rd.save(gc::updateRule)));
        bottom.setAlignment(Pos.CENTER);
        super.setBottom(bottom);
    }

}