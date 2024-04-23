package oogasalad.view.start;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LanguageDialogBox {
  private final StringProperty primaryLanguage = new SimpleStringProperty();
  private ComboBox<String> dropDownMenu;

  public LanguageDialogBox(String[] languages) {
    dropDownMenu = new ComboBox<>();

    for (String aLanguage : languages) {
      dropDownMenu.getItems().add(aLanguage);
    }

    dropDownMenu.setOnAction(e -> setPrimaryLanguage(dropDownMenu.getValue()));
  }

  public void open() {
    Stage newStage = new Stage();
    VBox root = new VBox();
    root.setPrefSize(200, 200);
    root.setAlignment(Pos.CENTER);
    root.getChildren().add(dropDownMenu);
    Scene newScene = new Scene(root);

    newStage.setScene(newScene);
    newStage.show();
  }

  public StringProperty primaryLanguageProperty() {
    return primaryLanguage;
  }

  private void setPrimaryLanguage(String language) {
    primaryLanguage.set(language);
  }
}
