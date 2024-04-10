package oogasalad.view.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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

  private static final Logger LOG = LogManager.getLogger(AbstractSplashScreen.class); // should this be here

  public AbstractSplashScreen() {
    // do nothing
  }




  /**
   * Opens SplashScreen
   */
  public abstract void open(Stage stageToUse);

  protected void setStage(Stage stage, double widthPortion, double heightPortion,
      String resourcePath, String buttonsPath, String stageTitle, String stylesCSS) {
    Scene scene;
    VBox vb = new VBox();
    vb.setAlignment(Pos.CENTER);
    vb.setSpacing(75);
    HBox hb = new HBox();
    hb.setSpacing(320);
    hb.setAlignment(Pos.CENTER);

    //Create the scene, initialized to a reasonable size.
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    int initialStartScreenWidth = (int) (screenBounds.getWidth() * widthPortion);
    int initialStartScreenHeight = (int) (screenBounds.getHeight() * heightPortion);

    // Create Start Buttons
    createButtonsFromFile(resourcePath + buttonsPath, hb);

    //Create title
    //TODO: Resources bundle this
    Label title = new Label(stageTitle);
    title.getStyleClass().add("title-label");
    title.widthProperty().addListener((obs, oldVal, newVal) -> titleBob(title, newVal));

    //create scene
    vb.getChildren().add(title);
    vb.getChildren().add(hb);
    scene = new Scene(vb, initialStartScreenWidth,
        initialStartScreenHeight);

    //link scene and css
    scene.getStylesheets().add(getClass().getResource(stylesCSS).toExternalForm());

    stage.setTitle(stageTitle);
    stage.setScene(scene);
    stage.show();
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
    path.getElements().addAll(
        new MoveTo(l.getLayoutX() + newVal.doubleValue() / 2, l.getLayoutY()),
        new LineTo(l.getLayoutX() + newVal.doubleValue() / 2, l.getLayoutY() + 10));

    PathTransition pt = new PathTransition(Duration.seconds(1), path, l);
    return new SequentialTransition(l, pt);
  }

  protected void createButtonsFromFile(String filename, HBox root) {
    List<String[]> buttonData = readCSV(filename);
    makeButton(buttonData, root);
  }

  protected void makeButton(List<String[]> buttonData, HBox root) {

    for (String[] data : buttonData) {
      ChangePageButton button = new ChangePageButton(data[0], data[1]);
      button.setId(data[0]);
      String className = data[2];
      String methodName = data[3];
      String[] parameters = new String[data.length - 4];
      System.arraycopy(data, 4, parameters, 0, parameters.length);
      button.setOnAction(new ButtonActionHandler(className, methodName, new Stage(), parameters));

      root.getChildren().add(button);
    }
  }

  private List<String[]> readCSV(String filename) {
    List<String[]> data = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(", ");
        data.add(values);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }
}
