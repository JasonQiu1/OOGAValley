package oogasalad.view.start;

import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ButtonActionHandler implements EventHandler<ActionEvent> {
  private String className;
  private String methodName;
  private Stage stage;
  private String[] parameters;

  public ButtonActionHandler(String className, String methodName, Stage stage, String... parameters) {
    this.className = className;
    this.methodName = methodName;
    this.stage = stage;
    this.parameters = parameters;
  }

  @Override
  public void handle(ActionEvent event) {
    try {
      Class<?> clazz = Class.forName(className);
      Class<?>[] parameterTypes = new Class[parameters.length + 1];
      parameterTypes[0] = Stage.class;
      for (int i = 1; i < parameters.length + 1; i++) {
        parameterTypes[i] = String.class; // Assume all parameters are strings
      }
      Method method = clazz.getMethod(methodName, parameterTypes);
      Object instance = clazz.getDeclaredConstructor().newInstance();

      Object[] args = new Object[parameters.length + 1]; // +1 for the stage
      args[0] = stage; // first argument is the stage
      System.arraycopy(parameters, 0, args, 1, parameters.length); // copy remaining parameters

      method.invoke(instance, args); // invoke the method with all parameters

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
