package oogasalad.view.buttonmenu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import oogasalad.view.start.SplashUtils;

public class ButtonMenu {

  private Scene menuScene;
  private Stage menuStage;
  private final Stage primaryStage;
  private final Scene previousScene;
  private final String primaryLanguage;
  private final String buttonsFilePath;

  private final String STYLES = "/view/buttonMenu/menu_style.css";

  public ButtonMenu(Stage mainStage, String language, Scene backScene, String filePath) {
    primaryStage = mainStage;
    primaryLanguage = language;
    previousScene = backScene;
    buttonsFilePath = filePath;

    primaryStage.sceneProperty().addListener(new ChangeListener<Scene>() {
      @Override
      public void changed(ObservableValue<? extends Scene> observable, Scene oldValue,
          Scene newValue) {
        menuStage.close();
      }
    });
  }

  public void open() {
    menuStage = new Stage();
    menuStage.initStyle(StageStyle.UNDECORATED);
    VBox root = new VBox();
    root.setSpacing(10);
    root.getStyleClass().add("root_VBox");

    VBox topBox = new VBox();
    topBox.getStyleClass().add("top_box");

    Label title = new Label("Menu");
    topBox.getChildren().add(title);
    topBox.setAlignment(Pos.CENTER);

    root.getChildren().add(topBox);

    SplashUtils.createButtonsFromFile(buttonsFilePath, primaryStage, root, primaryLanguage,
        previousScene);

    Group group = new Group(root);

    ScrollPane scrollPane = new ScrollPane(group); // Put the Group inside a ScrollPane
    scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER); // Disable horizontal scroll bar
    scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED); // Enable vertical scroll bar
    scrollPane.setPrefHeight(400); // Set a fixed size for the scroll pane
    scrollPane.setFitToWidth(true); // Disable fitting to width
    scrollPane.setFitToHeight(false); // Disable fitting to height

//    VBox.setVgrow(root, Priority.ALWAYS);
    root.setAlignment(Pos.CENTER);
    root.minHeightProperty().bind(scrollPane.heightProperty());
    root.prefHeightProperty().bind(scrollPane.heightProperty());

    menuScene = new Scene(scrollPane);
    menuScene.getStylesheets().add(getClass().getResource(STYLES).toExternalForm());

    if (primaryStage != null) {
      menuStage.initOwner(primaryStage);
      Window owner = primaryStage.getScene().getWindow();
      if (owner != null) {
        Bounds bounds = primaryStage.getScene().getRoot().getLayoutBounds();
        Point2D topLeft = new Point2D(bounds.getMaxX()/2 - 50, bounds.getMaxY()/2 - 200);
        Point2D screenTopLeft = primaryStage.getScene().getRoot().localToScreen(topLeft);
        menuStage.setX(screenTopLeft.getX());
        menuStage.setY(screenTopLeft.getY());
      }
    }

    menuStage.setScene(menuScene);
    menuStage.show();

  }

  public void closeMenu() {
      menuStage.close();
  }

}
