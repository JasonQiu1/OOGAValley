package oogasalad.view;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class StartScreen {
    public static final double DEFAULT_WIDTH_PORTION = 0.65;
    public static final double DEFAULT_HEIGHT_PORTION = 0.9;
    private final Stage stage;

    /**
     * Creates StartScreen
     * @param stageToUse
     */
    public StartScreen(Stage stageToUse) {
        stage = stageToUse;
    }

    /**
     * Opens StartScreen
     */
    public void open() {
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        HBox hb = new HBox();

        //Create the scene, initialized to a reasonable size.
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        int initialStartScreenWidth = (int) (screenBounds.getWidth() * DEFAULT_WIDTH_PORTION);
        int initialStartScreenHeight = (int) (screenBounds.getHeight() * DEFAULT_HEIGHT_PORTION);

        //Create title and put it in the scene
        //TODO: Resources bundle this
        Label title = new Label("OOGAVALLEY");
        title.getStyleClass().add("title-label");
        title.widthProperty().addListener((obs, oldVal, newVal) -> titleBob(title, newVal));
        vb.getChildren().add(title);
        Scene startScreen = new Scene(vb, initialStartScreenWidth,
                initialStartScreenHeight);

        //link scene and css
        startScreen.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());



        stage.setTitle("OOGAVALLEY 2024");
        stage.setScene(startScreen);
        stage.show();
    }

    private void titleBob(Label l, Number newVal){
        Animation animation = createAnimation(l, newVal);
        animation.play();
        animation.setOnFinished(event -> {
            animation.setRate(-animation.getRate());
            animation.play();
        });
    }

    private Animation createAnimation(Label l, Number newVal) {
        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(l.getLayoutX() + newVal.doubleValue() /2, l.getLayoutY()),
                new LineTo(l.getLayoutX() + newVal.doubleValue() /2, l.getLayoutY() + 10));

        PathTransition pt = new PathTransition(Duration.seconds(1), path, l);
        return new SequentialTransition(l, pt);
    }


}
