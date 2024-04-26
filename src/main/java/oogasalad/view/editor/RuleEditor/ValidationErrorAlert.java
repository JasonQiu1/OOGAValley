package oogasalad.view.editor.RuleEditor;

import javafx.scene.control.Alert;

public class ValidationErrorAlert {

  //TODO: Resource Bundle
  public ValidationErrorAlert(String ruleName, String ruleValue, String type) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Validation Error");
    alert.setHeaderText(null);
    alert.setContentText(
        "Rule: " + ruleName + " must be of type " + type + "\nEntered: " + ruleValue);
    alert.showAndWait();
  }
}
