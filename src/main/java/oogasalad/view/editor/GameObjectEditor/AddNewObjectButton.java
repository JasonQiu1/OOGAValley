package oogasalad.view.editor.GameObjectEditor;

import javafx.scene.control.Button;
import java.util.function.BiConsumer;

/**
 * Button for adding a new game object.
 */
public class AddNewObjectButton extends Button {

    /**
     * Constructs an AddNewObjectButton.
     *
     * @param newObject The BiConsumer function to handle the creation of a new object.
     */
    public AddNewObjectButton(BiConsumer<String, String> newObject) {
        super("New");
        setId("NewObject");

        // Create an AddNewObjectDialogBox instance
        AddNewObjectDialogBox anob = new AddNewObjectDialogBox();

        // Set action event for the button
        super.setOnAction(e -> {
            // Display dialog box and get new object type and name
            String[] newTypeAndName = anob.getNewField();
            // If user entered valid values, call the newObject BiConsumer
            if(newTypeAndName != null){
                newObject.accept(newTypeAndName[0], newTypeAndName[1]);
            }
        });
    }
}
