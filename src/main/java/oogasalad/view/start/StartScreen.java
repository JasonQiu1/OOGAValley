package oogasalad.view.start;

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
import oogasalad.view.editor.EditorScene;
import oogasalad.view.editor.EditorWindow;
import oogasalad.view.playing.PlayingView;


public class StartScreen {
    public static final double DEFAULT_WIDTH_PORTION = 0.65;
    public static final double DEFAULT_HEIGHT_PORTION = 0.9;
    private final Stage stage;
    private PlayingView playingView;
    private EditorScene editorScene;

    /**
     * Creates StartScreen
     * @param stageToUse
     */
    public StartScreen(Stage stageToUse) {
        stage = stageToUse;
        playingView = new PlayingView();
        editorScene = new EditorScene();
    }

    /**
     * Opens StartScreen
     */
    public void open() {
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(75);
        HBox hb = new HBox();
        hb.setSpacing(320);
        hb.setAlignment(Pos.CENTER);

        //Create the scene, initialized to a reasonable size.
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        int initialStartScreenWidth = (int) (screenBounds.getWidth() * DEFAULT_WIDTH_PORTION);
        int initialStartScreenHeight = (int) (screenBounds.getHeight() * DEFAULT_HEIGHT_PORTION);

        //Create Page Change Buttons
        ChangePageButton playGame = new ChangePageButton("Play", "lightgreen", e -> {
            try {
                playingView.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        ChangePageButton createGame = new ChangePageButton("Create", "lightblue", e -> {
            stage.setScene(editorScene);
        });
        hb.getChildren().add(createGame);
        hb.getChildren().add(playGame);

        //Create title
        //TODO: Resources bundle this
        Label title = new Label("OOGAVALLEY");
        title.getStyleClass().add("title-label");
        title.widthProperty().addListener((obs, oldVal, newVal) -> titleBob(title, newVal));

        //create scene
        vb.getChildren().add(title);
        vb.getChildren().add(hb);
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
