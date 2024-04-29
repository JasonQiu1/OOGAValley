package oogasalad.view.editor;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import oogasalad.model.data.GameConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AddPhotoButton extends Button {
    public AddPhotoButton(Window window, Consumer<File> savePhoto) {
        super("Add Photo"); //TODO: Resource Bundle
        setOnAction(e -> {
            File file = openFileChooser(window);
            if(file != null){
                savePhoto.accept(openFileChooser(window));
            }
        });

    }

    private File openFileChooser(Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");

        // Set the initial directory (optional)
        // fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Set filters for file types (optional)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(window);

        // Handle the selected file (e.g., load it into your application)
        if (selectedFile != null) {
            return selectedFile;
        }
        return null;
    }
}
