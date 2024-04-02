package oogasalad.view.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    public static final String DEFAULT_RESOURCE_FOLDER = "src/main/resources/";
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

        // Create Start Buttons
        createButtonsFromFile(DEFAULT_RESOURCE_FOLDER + "StartScreenButtonsInfo.csv", hb, stage);


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


    public static void createButtonsFromFile(String filename, HBox root, Stage stage) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 4) {
                    makeButton(parts[0], parts[1], parts[2], parts[3], root, stage);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void makeButton(String label, String color, String className, String methodName,
        HBox root, Stage stage) {
        try {
            System.out.println(className);
            // Load the class dynamically
            Class<?> handlerClass = Class.forName(className);
            Object handlerInstance = handlerClass.getDeclaredConstructor().newInstance();

            // Find the method dynamically
            Method method = handlerClass.getMethod(methodName, Stage.class);

            // Create button
            ChangePageButton button = new ChangePageButton(label, color);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        // Invoke the method when the button is clicked, passing the Stage parameter
                        method.invoke(handlerInstance, stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            root.getChildren().add(button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
