package oogasalad.view.editor.RuleEditor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import oogasalad.model.data.GameConfiguration;

public class RuleEditor extends Pane {
    GameConfiguration config;
    public RuleEditor(GameConfiguration gc){
        super();
        config = gc;
        BorderPane bp = new BorderPane();
        Label l = new Label("Rule Editor");
        l.getStyleClass().add("editor-label");
        RuleDisplay rd = new RuleDisplay(config.getRules().getCopyOfProperties());
        VBox vbox = new VBox();
        vbox.getChildren().addAll(l, rd);
        bp.setTop(vbox);
        HBox bottom = new HBox(new SaveButton(e -> rd.save(config::updateRule)));
        bottom.setAlignment(Pos.CENTER);
        bp.setBottom(bottom);
        super.setPadding(new Insets(0, 10, 10, 10));
        super.getChildren().add(bp);

    }

    public void setConfig(GameConfiguration gc) {
        config = gc;
    }
}