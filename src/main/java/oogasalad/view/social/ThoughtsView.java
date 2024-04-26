package oogasalad.view.social;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oogasalad.database.info.InfoService;
import oogasalad.database.info.Thought;

public class ThoughtsView extends Application {

  private Stage primaryStage;

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;

    VBox thoughtBox = new VBox();
    thoughtBox.setSpacing(10);
    thoughtBox.setPadding(new Insets(10));

    updateThoughtsView(thoughtBox);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(thoughtBox);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    Button sendThoughtButton = new Button("Send Thought");
    sendThoughtButton.setOnAction(e -> openSendThoughtPopup());

    VBox root = new VBox(scrollPane, sendThoughtButton);
    Scene scene = new Scene(root, 400, 350);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void updateThoughtsView(VBox thoughtBox) {
    thoughtBox.getChildren().clear();
    List<Thought> thoughts = InfoService.getAllThoughts();

    for (Thought thought : thoughts) {
      Label usernameLabel = new Label("Username: " + thought.getUsername());
      Label messageLabel = new Label("Message: " + thought.getText());
      Label timeLabel = new Label("Time: " + thought.getTime());

      VBox thoughtDetails = new VBox(usernameLabel, messageLabel, timeLabel);
      thoughtDetails.setStyle(
          "-fx-border-color: #000000; -fx-border-width: 1px; -fx-padding: 5px;");
      thoughtBox.getChildren().add(thoughtDetails);
    }
  }

  private void openSendThoughtPopup() {
    Stage popupStage = new Stage();
    popupStage.initModality(Modality.APPLICATION_MODAL);
    popupStage.setTitle("Send Thought");

    BorderPane borderPane = new BorderPane();
    TextArea thoughtTextArea = new TextArea();
    Button submitButton = new Button("Submit");
    submitButton.setOnAction(e -> {
      String thoughtText = thoughtTextArea.getText();
      int userId = 1;
      LocalDateTime currentTime = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String formattedTime = currentTime.format(formatter);
      InfoService.addThought(userId, thoughtText, formattedTime);
      updateThoughtsView(
          (VBox) ((ScrollPane) primaryStage.getScene().getRoot().getChildrenUnmodifiable()
              .get(0)).getContent());
      popupStage.close();
    });

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> popupStage.close());

    HBox buttonBox = new HBox(submitButton, backButton);
    buttonBox.setSpacing(10);

    borderPane.setCenter(thoughtTextArea);
    borderPane.setBottom(buttonBox);

    Scene popupScene = new Scene(borderPane, 300, 200);
    popupStage.setScene(popupScene);
    popupStage.showAndWait();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
