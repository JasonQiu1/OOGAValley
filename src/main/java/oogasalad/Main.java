package oogasalad;


import javafx.application.Application;
import javafx.stage.Stage;
import oogasalad.view.StartScreen;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    /**
     * A method to test (and a joke :).
     */
    public double getVersion () {
        return 0.001;
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        Main m = new Main();
        System.out.println(m.getVersion());
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        new StartScreen(primaryStage).open();
    }
}
