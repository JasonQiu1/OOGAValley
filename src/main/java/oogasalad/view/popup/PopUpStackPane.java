package oogasalad.view.popup;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import oogasalad.view.popup.buttons.NoButton;
import oogasalad.view.popup.buttons.YesButton;

/**
 * This class is a StackPane that creates a pop-up window with a message and two buttons. The
 * buttons are YesButton and NoButton. The pop-up window is added to the parentStackPane. The
 * choiceCallback is a Consumer that takes a boolean as an argument. The boolean is true if the user
 * clicks the YesButton and false if the user clicks the NoButton.
 */
public class PopUpStackPane extends StackPane {

  private final String popUpMessage;
  private final YesButton yesButton;
  private final NoButton noButton;
  private final Consumer<Boolean> choiceCallback;
  private final StackPane parentStackPane;

  /**
   * Constructor for PopUpStackPane
   *
   * @param popUpMessage    the message to be displayed in the pop-up window
   * @param yesButtonText   the text to be displayed on the YesButton
   * @param noButtonText    the text to be displayed on the NoButton
   * @param parentStackPane the StackPane to which the pop-up window will be added
   * @param choiceCallback  the Consumer that takes a boolean as an argument
   */
  public PopUpStackPane(String popUpMessage, String yesButtonText, String noButtonText,
      StackPane parentStackPane,
      Consumer<Boolean> choiceCallback) {
    super();
    this.popUpMessage = popUpMessage;
    this.yesButton = new YesButton(yesButtonText);
    this.noButton = new NoButton(noButtonText);
    this.parentStackPane = parentStackPane;
    this.choiceCallback = choiceCallback;
    initialize();
  }

  private void initialize() {
    setBackground(createBackground());
    setUpContent();
    setUpButton();
  }

  private Background createBackground() {
    Image backgroundImage = new Image("img/pop-up/bg-shadow.png");
    BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
        BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    return new Background(background);
  }

  private void setUpContent() {
    StackPane centerStackPane = createCenterStackPane();
    getChildren().addAll(centerStackPane);
  }

  private StackPane createCenterStackPane() {
    StackPane centerStackPane = new StackPane();
    centerStackPane.setPrefSize(Utils.centerWidth, Utils.centerHeight);
    centerStackPane.getChildren().addAll(createCenterBackgroundImageView(), createVBox());
    return centerStackPane;
  }

  private ImageView createCenterBackgroundImageView() {
    Image centerBackgroundImage = new Image("img/pop-up/pop-up-background.png");
    ImageView centerBackgroundImageView = new ImageView(centerBackgroundImage);
    centerBackgroundImageView.setFitWidth(Utils.centerWidth);
    centerBackgroundImageView.setFitHeight(Utils.centerHeight);
    return centerBackgroundImageView;
  }

  private VBox createVBox() {
    VBox vBox = new VBox(Utils.padding);
    vBox.getChildren().addAll(createMessageLabel(), createButtonBox());
    vBox.setAlignment(Pos.CENTER);
    return vBox;
  }

  private Label createMessageLabel() {
    Label messageLabel = new Label(popUpMessage);
    messageLabel.getStyleClass().add("pop-up-label");
    return messageLabel;
  }

  private HBox createButtonBox() {
    HBox buttonBox = new HBox(Utils.padding);
    buttonBox.getChildren().addAll(yesButton, noButton);
    buttonBox.setAlignment(Pos.CENTER);
    return buttonBox;
  }

  private void setUpButton() {
    yesButton.setOnAction(event -> {
      choiceCallback.accept(true);
      closePopup();
    });

    noButton.setOnAction(event -> {
      choiceCallback.accept(false);
      closePopup();
    });
  }

  private void closePopup() {
    parentStackPane.getChildren().remove(this);
  }
}
