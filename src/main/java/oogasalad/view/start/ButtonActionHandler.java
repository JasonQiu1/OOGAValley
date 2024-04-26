package oogasalad.view.start;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import oogasalad.model.data.GameConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ButtonActionHandler implements EventHandler<ActionEvent> {

  private static final Logger LOG = LogManager.getLogger(StartScreen.class);
  private final String className;
  private final String methodName;
  private final Stage stage;
  private String language;
  private final String[] parameters;

  public ButtonActionHandler(String className, String methodName, Stage stage, String language,
      String... parameters) {
    this.className = className;
    this.methodName = methodName;
    this.stage = stage;
    this.language = language;
    this.parameters = parameters.clone();
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
      System.out.println(clazz.getSimpleName());
      Constructor<?> constructor = clazz.getConstructor(Stage.class, String.class, GameConfiguration.class);
      Object instance = constructor.newInstance(stage, language, new GameConfiguration());

      Object[] args = new Object[parameters.length]; // +1 for the stage
      System.arraycopy(parameters, 0, args, 0, parameters.length); // copy remaining parameters

      method.invoke(instance, args); // invoke the method with all parameters

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
