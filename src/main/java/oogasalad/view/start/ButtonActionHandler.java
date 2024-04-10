package oogasalad.view.start;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ButtonActionHandler implements EventHandler<ActionEvent> {

  private final String className;
  private final String methodName;
  private final Stage stage;
  private final String[] parameters;
  private static final Logger LOG = LogManager.getLogger(StartScreen.class);

  public ButtonActionHandler(String className, String methodName, Stage stage,
      String... parameters) {
    this.className = className;
    this.methodName = methodName;
    this.stage = stage;
    this.parameters = parameters;
  }

  @Override
  public void handle(ActionEvent event) {
    try {
      Class<?> clazz = Class.forName(className);
      Class<?>[] parameterTypes = new Class[parameters.length];
      LOG.info(parameterTypes.length);
      for (int i = 0; i < parameters.length; i++) {
        parameterTypes[i] = String.class; // Assume all parameters are strings
      }
//      LOG.info(parameterTypes[0]);
      Method method = clazz.getMethod(methodName, parameterTypes);

      Constructor<?> constructor = clazz.getConstructor(Stage.class);
      Object instance = constructor.newInstance(stage);

      Object[] args = new Object[parameters.length]; // +1 for the stage
      System.arraycopy(parameters, 0, args, 0, parameters.length); // copy remaining parameters

      method.invoke(instance, args); // invoke the method with all parameters

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
