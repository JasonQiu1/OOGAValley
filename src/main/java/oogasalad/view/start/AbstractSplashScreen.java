package oogasalad.view.start;

import java.util.List;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractSplashScreen {
  private static final Logger LOG = LogManager.getLogger(AbstractSplashScreen.class);
      // should this be here
  private Scene previousScene;

  public AbstractSplashScreen() {
    // do nothing
  }

  /**
   * Opens SplashScreen
   */
  public abstract void open();

  protected Scene setStage(Stage stage, double widthPortion, double heightPortion,
      ResourceString resourceString, String language, Scene myScene) {

    previousScene = myScene;

    Scene scene;
    VBox vb = new VBox();
    vb.setAlignment(Pos.CENTER);
    vb.setSpacing(75);
    VBox buttonsBox = new VBox();
    buttonsBox.setSpacing(10);
    buttonsBox.setAlignment(Pos.CENTER);

    //Create the scene, initialized to a reasonable size.
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    int initialStartScreenWidth = (int) (screenBounds.getWidth() * widthPortion);
    int initialStartScreenHeight = (int) (screenBounds.getHeight() * heightPortion);


    //Create title
    //TODO: Resources bundle this
    Label title = new Label(resourceString.stageTitle());
    title.getStyleClass().add("title-label");
    title.widthProperty().addListener((obs, oldVal, newVal) -> titleBob(title, newVal));

    //create scene
    vb.getChildren().add(title);
    scene = new Scene(vb, initialStartScreenWidth, initialStartScreenHeight);
//    myScene = scene;

    createButtonsFromFile(resourceString.buttonsPath(), stage, buttonsBox, language, scene);
    vb.getChildren().add(buttonsBox);

    LOG.info(myScene);

    //link scene and css
    scene.getStylesheets().add(getClass().getResource(resourceString.styleCss()).toExternalForm());

    return scene;
  }

  protected void titleBob(Label l, Number newVal) {
    Animation animation = createAnimation(l, newVal);
    animation.play();
    animation.setOnFinished(event -> {
      animation.setRate(-animation.getRate());
      animation.play();
    });
  }

  protected Animation createAnimation(Label l, Number newVal) {
    Path path = new Path();
    path.getElements().addAll(new MoveTo(l.getLayoutX() + newVal.doubleValue() / 2, l.getLayoutY()),
        new LineTo(l.getLayoutX() + newVal.doubleValue() / 2, l.getLayoutY() + 10));

    PathTransition pt = new PathTransition(Duration.seconds(1), path, l);
    return new SequentialTransition(l, pt);
  }

  protected void createButtonsFromFile(String filename, Stage primaryStage, VBox root,
      String language, Scene myScene) {
    List<String[]> buttonData = SplashUtils.readCommaSeparatedCSVLines(filename);
    makeButton(buttonData, primaryStage, root, language, myScene);
  }

  protected void makeButton(List<String[]> buttonData, Stage primaryStage, VBox root,
      String language, Scene myScene) {

    for (String[] data : buttonData) {
      ChangePageButton button = new ChangePageButton(data[0], data[1]);
      String className = data[2];
      String methodName = data[3];
      String[] parameters = new String[data.length - 4];
      System.arraycopy(data, 4, parameters, 0, parameters.length);
      LOG.debug(String.format("this is the previous scene %s to %s", this.getClass(), className));
      LOG.debug(String.valueOf(this.getClass()).equals("class " + className));
      if (String.valueOf(this.getClass()).equals("class " + className)) {
        LOG.debug(String.format("in if"));
        button.setOnAction(
            new ButtonActionHandler(className, methodName, primaryStage, language, previousScene,
                parameters));
      } else {
        button.setOnAction(
            new ButtonActionHandler(className, methodName, primaryStage, language, myScene,
                parameters));
      }
      // TODO: Fix this

      root.getChildren().add(button);
    }
  }

}
